package com.kforce.rewards.service;

import com.kforce.rewards.dto.TransactionsList;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public interface TransactionsService {

    ResponseEntity<TransactionsList> findAllRewardPointsForCustomerBetweenDates(Long customerId, LocalDate startDate, LocalDate endDate);
}
