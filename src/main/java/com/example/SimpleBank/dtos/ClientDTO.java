package com.example.SimpleBank.dtos;

import java.math.BigDecimal;

public record ClientDTO(String name, String surname, BigDecimal balance, String email, String password) {
}
