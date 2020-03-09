package com.kforce.rewards.repository;

import com.kforce.rewards.entity.TransactionsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface TransactionsRepository extends JpaRepository<TransactionsEntity, Integer> {

//    Page<TransactionsEntity> findAll(Pageable pageable);

//    ResponseApiDTO findCustomerTransactionByCustomerId(Integer customerId, Integer transactionId) throws IOException;

    TransactionsEntity findTransactionsEntityByTransactionName(String transactionName);

    Optional<TransactionsEntity> findByCustomersByCustomerId_CustomerIdAndTransactionId(Integer customerId, Integer transactionId);

    List<TransactionsEntity> findByCustomersByCustomerId_CustomerId(Integer customerId);

    List<TransactionsEntity> findByCustomersByCustomerId_CustomerIdAndTransactionDateGreaterThanEqualOrderByTransactionDateAsc(Integer customerId, LocalDate startDate);

}
