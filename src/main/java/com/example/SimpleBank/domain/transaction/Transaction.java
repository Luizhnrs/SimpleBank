package com.example.SimpleBank.domain.transaction;


import com.example.SimpleBank.domain.client.Client;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity(name = "transaction")
@Table(name = "transaction")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private BigDecimal value;
    @ManyToOne
    @JoinColumn(name = "sender_id")
    private Client sender;
    @ManyToOne
    @JoinColumn(name = "receiver_id")
    private Client receiver;

    private LocalDateTime timeStamp;

    public Object setValue(Transaction value) {
        return null;
    }

    public void setAmount(Transaction value) {
        return;
    }

    public void setSender(Client sender) {
        return;
    }

    public void setReceiver(Client receiver) {
        return;
    }

    public void setTimestamp(LocalDateTime now) {
        return;
    }

    public Transaction amount() {
        return null;
    }

}
