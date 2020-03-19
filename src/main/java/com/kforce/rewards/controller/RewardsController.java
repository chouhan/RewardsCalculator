package com.kforce.rewards.controller;

import com.kforce.rewards.dto.TransactionsList;
import com.kforce.rewards.service.TransactionsService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

@RestController
@RequestMapping(value = {"/api"}, produces = "application/vnd.rc-v1.0+json")
@Api(value = "Rewards API Controller")
public class RewardsController {

    @Autowired
    private TransactionsService transactionService;

    // Calculate Rewards for a Customer, for given dates
    @GetMapping(value = "/transactions/customer/{customer_id}/calculate", produces = "application/json")
    private ResponseEntity<TransactionsList> calculateAllRewardPointsForCustomerBetweenDates(
            @PathVariable("customer_id") Long customerId,
            @RequestParam("start_date") String startDate,
            @RequestParam(value = "end_date", required = false) String endDate) {

        LocalDate sDate = null;
        LocalDate eDate = null;

        try {
            sDate = LocalDate.parse(startDate, DateTimeFormatter.ofPattern("MM-dd-yyyy"));
            eDate = StringUtils.isEmpty(endDate) ? LocalDate.now() : LocalDate.parse(endDate, DateTimeFormatter.ofPattern("MM-dd-yyyy"));
        } catch (DateTimeParseException e) {
            throw e;
        }

        if (eDate.isBefore(sDate)) {
            throw new IllegalArgumentException("Start date cannot be greater than end date");
        }

        return transactionService.findAllRewardPointsForCustomerBetweenDates(customerId, sDate, eDate);
    }
}
