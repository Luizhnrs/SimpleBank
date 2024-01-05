package com.example.SimpleBank.services;

import com.example.SimpleBank.domain.client.Client;
import com.example.SimpleBank.dtos.MessageDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class MessageService {
    @Autowired
    private RestTemplate restTemplate;

    public void sendMessage(Client client, String message){
        String email = client.getEmail();
        MessageDTO messageRequest = new MessageDTO(email, message);


        ResponseEntity <String> messageResponse = restTemplate.postForEntity("https://3l531.wiremockapi.cloud/", messageRequest, String.class);
    }


}
