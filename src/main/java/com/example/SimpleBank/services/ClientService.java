package com.example.SimpleBank.services;

import com.example.SimpleBank.domain.client.Client;
import com.example.SimpleBank.domain.client.ClientType;
import com.example.SimpleBank.dtos.ClientDTO;
import com.example.SimpleBank.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.crypto.Cipher;
import java.math.BigDecimal;

@Service
public class ClientService {
    @Autowired
    private ClientRepository repository;

    public void validateTransaction(Client sender, BigDecimal value) throws Exception {
        if(sender.getClientType() == ClientType.ETREPREUNEUR){
            throw new Exception("This entrepreneur tier user is not able to make this transaction");
        }
        if(sender.getBalance().compareTo(value) < 0){
            throw new Exception("This client does not have enough balance to complete the transaction");
        }
    }
    public Client findClientById(Long id) throws Exception {
       return this.repository.findClientById(id).orElseThrow(() -> new Exception("Client Not Found"));
    }

    public Client createClient(ClientDTO data){
        Client newClient = new Client(data);
        this.saveClient(newClient);
        return newClient;
    }

    public void saveClient(Client client ){
        this.repository.save(client);
    }
}
