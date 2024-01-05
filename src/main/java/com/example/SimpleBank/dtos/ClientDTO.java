package com.example.SimpleBank.dtos;

import com.example.SimpleBank.domain.client.ClientType;

import java.math.BigDecimal;

public record ClientDTO(String name, String surname, BigDecimal balance, String email, String password, ClientType clientType) {
}
