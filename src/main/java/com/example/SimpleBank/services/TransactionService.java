package com.example.SimpleBank.services;

import com.example.SimpleBank.domain.client.Client;
import com.example.SimpleBank.domain.transaction.Transaction;
import com.example.SimpleBank.dtos.TransactionDTO;
import com.example.SimpleBank.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransactionService {
    @Autowired
    private ClientService clientService;

    @Autowired
    private TransactionRepository repository;

    public void createTransaction (TransactionDTO transaction) throws Exception {
        Client sender = this.clientService.findClientById(transaction.senderId());
        Client receiver = this.clientService.findClientById(transaction.receiverId());
    }
}
