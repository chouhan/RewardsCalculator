package com.kforce.rewards.entity;

import com.fasterxml.jackson.annotation.JsonFilter;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;

@Entity
@Table(name = "CUSTOMERS", schema = "POINTS")
@Getter
@Setter
@NoArgsConstructor
@JsonFilter("customersFilter")
public class CustomersEntity implements Serializable {

    /**
     * Generated Serial ID
     */
    private static final long serialVersionUID = -354397830345539477L;

    @Id
    @Column(name = "CUSTOMER_ID", nullable = false)
    private int customerId;

    @Column(name = "TELEPHONE_NUMBER", nullable = false, length = 255)
    private String telephoneNumber;

    @Column(name = "EMAIL_ADDRESS", nullable = false, length = 255)
    private String emailAddress;

    @Column(name = "FIRST_NAME", nullable = false, length = 255)
    private String firstName;

    @Column(name = "LAST_NAME", nullable = false, length = 255)
    private String lastName;

    @Column(name = "STATUS", nullable = false, length = 255)
    private String status;

    @Column(name = "USER_NAME", nullable = false, length = 255)
    private String userName;

    @OneToMany(mappedBy = "customersByCustomerId", cascade = {CascadeType.ALL, CascadeType.PERSIST, CascadeType.MERGE})
    private Collection<TransactionsEntity> transactionsByCustomerId;


    public Collection<TransactionsEntity> getTransactionsByCustomerId() {
        return transactionsByCustomerId;
    }

    public void setTransactionsByCustomerId(Collection<TransactionsEntity> transactionsByCustomerId) {
        this.transactionsByCustomerId = transactionsByCustomerId;
    }
}
