package com.kforce.rewards.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "TRANSACTIONS", schema = "POINTS")
@Getter
@Setter
@NoArgsConstructor
public class TransactionsEntity {
    @Id
    @Column(name = "TRANSACTION_ID", nullable = false)
    private int transactionId;

    @Column(name = "TRANSACTION_NAME", nullable = false, length = 255)
    private String transactionName;

    @Column(name = "TRANSACTION_DATE", nullable = false)
    private LocalDate transactionDate;

    @Column(name = "TRANSACTION_AMOUNT", nullable = false, precision = 2)
    private BigDecimal transactionAmount;

    @ManyToOne
    @JoinColumn(name = "CUSTOMER_ID", referencedColumnName = "CUSTOMER_ID")
    private CustomersEntity customersByCustomerId;

    @Transient
    private Integer points;
}
