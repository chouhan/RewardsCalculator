package com.kforce.rewards.controller;

import com.kforce.rewards.dto.Customers;
import com.kforce.rewards.dto.Transactions;
import com.kforce.rewards.response.ResponseApiDTO;
import com.kforce.rewards.service.CustomerService;
import com.kforce.rewards.service.TransactionService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping(value = {"/api"}, produces = "application/vnd.rc-v1.0+json")
@Api(value = "Rewards API Controller")
public class RewardsController {

    @Autowired
    private CustomerService customerService;

    @Autowired
    private TransactionService transactionService;

    @GetMapping(value = "/customers/", produces = "application/json")
    private ResponseApiDTO<Customers> getAllCustomers() throws IOException {
        return customerService.findAllCustomers();
    }

    @GetMapping(value = "/customers", produces = "application/json")
    private ResponseApiDTO<Customers> getCustomerByUserName(@RequestParam("user_name") String userName) throws IOException {
        return customerService.findCustomerByUserName(userName);
    }

    @GetMapping(value = "/customers/{customer_id}", produces = "application/json")
    private ResponseApiDTO<Customers> getCustomerById(@PathVariable("customer_id") Integer customerId) throws IOException {
        return customerService.findCustomerByCustomerId(customerId);
    }


    @GetMapping(value = "/transactions/", produces = "application/json")
    private ResponseApiDTO<Transactions> getAllTransactions() throws IOException {
        return transactionService.findAllTransactions();
    }

    @GetMapping(value = "/transactions", produces = "application/json")
    private ResponseApiDTO<Customers> getTransactionByTransactionName(@RequestParam("transaction_name") String transactionName) throws IOException {
        return transactionService.findTransactionByTransactionName(transactionName);
    }

    @GetMapping(value = "/transactions/{transaction_id}", produces = "application/json")
    private ResponseApiDTO<Customers> getTransactionById(@PathVariable("transaction_id") Integer transactionId) throws IOException {
        return transactionService.findTransactionByTransactionId(transactionId);
    }

    @GetMapping(value = "/customers/{customer_id}/transactions", produces = "application/json")
    private ResponseApiDTO<Customers> getAllTransactionsByCustomerId(
            @PathVariable("customer_id") Integer customerId) throws IOException {
        return transactionService.findAllTransactionsByCustomerId(customerId);
    }

    // Get Customer Info from a given transaction id
    @GetMapping(value = "/transactions/{transaction_id}/customer", produces = "application/json")
    private ResponseApiDTO<Customers> getCustomerByTransactionId(
            @PathVariable("transaction_id") Integer transactionId) throws IOException {
        return transactionService.findCustomerByTransactionId(transactionId);
    }

    // Get a single transaction of a given customer by customer id
    @GetMapping(value = "/customers/{customer_id}/transactions/{transaction_id}", produces = "application/json")
    private ResponseApiDTO<Customers> getTransactionByCustomerIdAndTransactionId(
            @PathVariable("customer_id") Integer customerId,
            @PathVariable("transaction_id") Integer transactionId) throws IOException {
        return transactionService.findTransactionByCustomerIdAndTransactionId(customerId, transactionId);
    }


    // Get All Transactions between dates
    @GetMapping(value = "/transactions/customers/{customer_id}", produces = "application/json")
    private ResponseApiDTO<Transactions> getAllTransactionsForCustomerBetweenDates(
            @PathVariable("customer_id") Integer customerId,
            @RequestParam("start_date") String startDate,
            @RequestParam(value = "end_date", required = false) String endDate) throws IOException {
        return transactionService.findAllTransactionsForCustomerBetweenDates(customerId, startDate, endDate);
    }


    // Calculate Rewards for a Customer, for given dates
    @GetMapping(value = "/transactions/customers/{customer_id}/calculate", produces = "application/json")
    private ResponseApiDTO<Transactions> calculateAllRewardPointsForCustomerBetweenDates(
            @PathVariable("customer_id") Integer customerId,
            @RequestParam("start_date") String startDate,
            @RequestParam(value = "end_date", required = false) String endDate) throws IOException {
        return transactionService.findAllRewardPointsForCustomerBetweenDates(customerId, startDate, endDate);
    }
}
