package com.example.SimpleBank.services;

import com.example.SimpleBank.domain.client.Client;
import com.example.SimpleBank.domain.client.ClientType;
import com.example.SimpleBank.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class ClientService {
    @Autowired
    private ClientRepository repository;

    public void validateTransaction(Client sender, BigDecimal value) throws Exception {
        if(sender.getClientType() == ClientType.ETREPREUNEUR){
            throw new Exception("This entrepreneur tier user is not able to make this transaction");
        }
    }
}
