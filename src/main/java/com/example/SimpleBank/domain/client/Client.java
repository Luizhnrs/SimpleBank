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

    private ClientType clientType;

    public ClientType getClientType() {
        return this.clientType;
    }

    public Comparable<BigDecimal> getBalance() {
        return balance;
    }

}
