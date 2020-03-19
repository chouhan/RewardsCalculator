package com.kforce.rewards.repository;

import com.kforce.rewards.entity.TransactionsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface TransactionsRepository extends JpaRepository<TransactionsEntity, Long> {

    List<TransactionsEntity> findByCustomersByCustomerId_CustomerIdAndTransactionDateGreaterThanEqualOrderByTransactionDateAsc(Long customerId, LocalDate startDate);

}
