package com.example.SimpleBank.controllers;

import com.example.SimpleBank.domain.client.Client;
import com.example.SimpleBank.dtos.ClientDTO;
import com.example.SimpleBank.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping("/clients")
public class ClientController {
    @Autowired
    private ClientService clientService;
    @PostMapping
    public ResponseEntity<Client> createClient(ClientDTO client){
        Client newClient = clientService.createClient(client);
        return new ResponseEntity<>(newClient, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Client>> getAllClients(){
        List<Client> clients = this.clientService.getAllClients();
        return new ResponseEntity<>(clients, HttpStatus.OK);
    }
}
