package com.picpayDemo.picpayDemo.Domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity(name="transactions")
@Table(name="transactions")
@Getter
@AllArgsConstructor
@EqualsAndHashCode(of="id")
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "sender_id")
    private User sender;

    @ManyToOne
    @JoinColumn(name = "receiver_id")
    private User receiver;

    @Column(nullable = false)
    private BigDecimal amount;

    private LocalDateTime timestamp;

    public Transaction(User sender, User receiver, BigDecimal amount) {
        this.sender = sender;
        this.receiver = receiver;
        this.amount = amount;
    }
}
