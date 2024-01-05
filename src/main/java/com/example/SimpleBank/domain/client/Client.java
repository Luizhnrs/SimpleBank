package com.example.SimpleBank.domain.client;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity(name = "clients")
@Table(name = "clients")
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String surname;
    @Column(unique = true)
    private String document;
    @Column(unique = true)
    private String email;
    private String password;
    private BigDecimal balance;
    @Enumerated(EnumType.STRING)
    private ClientType clientType;

    public ClientType getClientType() {
        return this.clientType;
    }

    public Comparable<BigDecimal> getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal newBalance){
        this.balance = newBalance;
    }

    public String getEmail() {
        return email;
    }
}
