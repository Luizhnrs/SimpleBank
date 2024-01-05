package com.example.SimpleBank.services;

import com.example.SimpleBank.domain.client.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class MessageService {
    @Autowired
    private RestTemplate restTemplate;

    public void sendMessage(Client client, String message){
        String email = client.getEmail();
    }
}
