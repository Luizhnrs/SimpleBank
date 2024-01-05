package com.example.SimpleBank.controllers;

import com.example.SimpleBank.domain.client.Client;
import com.example.SimpleBank.dtos.ClientDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("/clients")
public class ClientController {
    @PostMapping
    public ResponseEntity<Client> createClient(ClientDTO client){

    }
}
