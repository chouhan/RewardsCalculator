package com.kforce.rewards.service;

import com.kforce.rewards.response.ResponseApiDTO;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public interface TransactionService {

    ResponseApiDTO findAllTransactions() throws IOException;

    ResponseApiDTO findTransactionByTransactionName(String transactionName) throws IOException;

    ResponseApiDTO findTransactionByTransactionId(Integer transactionId) throws IOException;

    ResponseApiDTO findAllTransactionsByCustomerId(Integer customerId) throws IOException;

    ResponseApiDTO findCustomerByTransactionId(Integer transactionId) throws IOException;

    ResponseApiDTO findTransactionByCustomerIdAndTransactionId(Integer customerId, Integer transactionId) throws IOException;

    ResponseApiDTO findAllTransactionsForCustomerBetweenDates(Integer customerId, String startDate, String endDate) throws IOException;

    ResponseApiDTO findAllRewardPointsForCustomerBetweenDates(Integer customerId, String startDate, String endDate) throws IOException;

}
