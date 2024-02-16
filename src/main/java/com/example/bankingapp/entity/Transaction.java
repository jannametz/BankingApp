package com.example.bankingapp.entity;

import com.example.bankingapp.entity.enums.TransactionType;

import jakarta.persistence.*;
import org.springframework.transaction.annotation.Transactional;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

/**
 * @author Jana Metz on 21.01.24
 */
@Entity
@Getter
@ToString
@Transactional
@NoArgsConstructor
@Table(name = "transactions")
public class Transaction {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "time_creation")
    private LocalTime timeCreation;

    @Column(name = "date_creation")
    private LocalDate dateCreation;

    @Column(name = "transaction_type")
    private TransactionType type;

    @Column(name = "account_to")
    private String accountTo;

    @Column(name = "account_from")
    private String accountFrom;

    @Column(name = "amount")
    private BigDecimal amount;

    public Transaction(TransactionType type, String accountFrom, String accountTo, Double amount) {
        this.type = type;
        this.accountFrom = accountFrom;
        this.accountTo = accountTo;
        this.amount = BigDecimal.valueOf(amount);
        this.timeCreation = LocalTime.now();
        this.dateCreation = LocalDate.now();
    }


}
