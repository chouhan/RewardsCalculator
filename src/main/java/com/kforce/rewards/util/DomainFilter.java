package com.kforce.rewards.util;

import lombok.Getter;

@Getter
public enum DomainFilter {

    customersFilter("customersFilter"),
    transactionsFilter("transactionsFilter");

    private final String value;

    DomainFilter(String value) {
        this.value = value;
    }
}
