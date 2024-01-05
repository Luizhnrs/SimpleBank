package com.example.SimpleBank.domain.client;

import com.example.SimpleBank.dtos.ClientDTO;
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

    public Client(ClientDTO data){
        this.name = data.name();
        this.surname = data.surname();
        this.balance = data.balance();
        this.clientType = data.clientType();
        this.password = data.password();
        this.email = data.email();
    }
}
