package domain.transaction;


import domain.client.Client;
import domain.client.ClientType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

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
}
