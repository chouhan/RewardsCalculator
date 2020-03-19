package com.kforce.rewards.controller;

import com.kforce.rewards.dto.TransactionsList;
import com.kforce.rewards.service.TransactionService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping(value = {"/api"}, produces = "application/vnd.rc-v1.0+json")
@Api(value = "Rewards API Controller")
public class RewardsController {

    @Autowired
    private TransactionService transactionService;

    // Calculate Rewards for a Customer, for given dates
    @GetMapping(value = "/transactions/customers/{customer_id}/calculate", produces = "application/json")
    private ResponseEntity<TransactionsList> calculateAllRewardPointsForCustomerBetweenDates(
            @PathVariable("customer_id") Integer customerId,
            @RequestParam("start_date") String startDate,
            @RequestParam(value = "end_date", required = false) String endDate) throws IOException {
        return transactionService.findAllRewardPointsForCustomerBetweenDates(customerId, startDate, endDate);
    }
}
