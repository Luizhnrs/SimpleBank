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

        BigDecimal valueToSubtract = new BigDecimal("");

        Transaction newTransaction = new Transaction();
        newTransaction.setValue(newTransaction.amount());
        newTransaction.setSender(sender);
        newTransaction.setReceiver(receiver);
        newTransaction.setTimestamp(LocalDateTime.now());
        transaction.subtractValue(valueToSubtract);

        sender.setBalance(sender.getBalance().valueToSubtract(transaction.amount()));


    }

    public boolean authorizeTransaction(Client sender, BigDecimal value){
        ResponseEntity <Map> authorizationResponse = restTemplate.getForEntity("https://run.mocky.io/v3/1d692d62-633e-46cf-a27b-ae448a4671f7", Map.class);

        if(authorizationResponse.getStatusCode() == HttpStatus.OK){
            String message = (String) authorizationResponse.getBody().get("message");
            return "Authorized".equalsIgnoreCase(message);
        }else return false;
    }
}
