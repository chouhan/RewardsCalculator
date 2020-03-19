package com.kforce.rewards.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class Transactions {

    @JsonProperty("transaction_id")
    private Integer transactionId;

    @JsonProperty("transaction_name")
    private String transactionName;

    @JsonProperty("transaction_date")
    private LocalDate transactionDate;

    @JsonProperty("transaction_amount")
    private String transactionAmount;

    @JsonProperty("points")
    private Integer points;
}
