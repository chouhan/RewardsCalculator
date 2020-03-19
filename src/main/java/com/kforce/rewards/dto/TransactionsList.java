package com.kforce.rewards.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;

@Getter
@Setter
public class TransactionsList {

    private List<Transactions> transactionsList;

    private Integer totalRewardPoints;

    private Map<String, Integer> pointsByMonth;

}
