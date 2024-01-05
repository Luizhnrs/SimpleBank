package com.example.SimpleBank.services;

import com.example.SimpleBank.domain.client.Client;
import com.example.SimpleBank.domain.transaction.Transaction;
import com.example.SimpleBank.dtos.TransactionDTO;
import com.example.SimpleBank.domain.transaction.Transaction;
import com.example.SimpleBank.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Map;

@Service
public class TransactionService {
    @Autowired
    private ClientService clientService;

    @Autowired
    private TransactionRepository repository;

    @Autowired
    private RestTemplate restTemplate;

    public void createTransaction (TransactionDTO transaction) throws Exception {
        Client sender = this.clientService.findClientById(transaction.senderId());
        Client receiver = this.clientService.findClientById(transaction.receiverId());

        clientService.validateTransaction(sender, transaction.amount());

        boolean isAuthorized = this.authorizeTransaction(sender,transaction.amount());
        if(!isAuthorized){
            throw new Exception("Transaction not authorized");
        }

        Transaction newTransaction = new Transaction();
        newTransaction.setValue(newTransaction.amount());
        newTransaction.setSender(sender);
        newTransaction.setReceiver(receiver);
        newTransaction.setTimestamp(LocalDateTime.now());

        BigDecimal senderBalance = new BigDecimal(sender.getBalance().toString());
        BigDecimal receiverBalance = new BigDecimal(receiver.getBalance().toString());

        senderBalance = senderBalance.subtract(transaction.amount());
        receiverBalance = receiverBalance.add(transaction.amount());

        sender.setBalance(senderBalance.subtract(transaction.amount()));
        receiver.setBalance(receiverBalance.add(transaction.amount()));

        this.repository.save(newTransaction);
        this.clientService.saveClient(sender);
        this.clientService.saveClient(receiver);

    }

    public boolean authorizeTransaction(Client sender, BigDecimal value){
        ResponseEntity <Map> authorizationResponse = restTemplate.getForEntity("https://run.mocky.io/v3/1d692d62-633e-46cf-a27b-ae448a4671f7", Map.class);

        if(authorizationResponse.getStatusCode() == HttpStatus.OK){
            String message = (String) authorizationResponse.getBody().get("message");
            return "Authorized".equalsIgnoreCase(message);
        }else return false;
    }
}
