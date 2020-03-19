package com.kforce.rewards.service;

import com.github.dozermapper.core.DozerBeanMapperBuilder;
import com.github.dozermapper.core.Mapper;
import com.kforce.rewards.dto.Transactions;
import com.kforce.rewards.dto.TransactionsList;
import com.kforce.rewards.entity.TransactionsEntity;
import com.kforce.rewards.repository.TransactionsRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.omg.IOP.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpServerErrorException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@Component
public class TransactionServiceImpl implements TransactionsService {

    private static final Logger LOGGER = LogManager.getLogger(TransactionService.class);

    @Autowired
    TransactionsRepository transactionsRepository;

    private Mapper beanMapper = DozerBeanMapperBuilder.buildDefault();

    @Cacheable(value = "rewardPointsCalculator")
    public ResponseEntity<TransactionsList> findAllRewardPointsForCustomerBetweenDates(Long customerId, LocalDate startDate, LocalDate endDate) {
        LOGGER.info("Inside " + this.getClass() + " findAllRewardPointsForCustomerBetweenDates method");

        // Get all transactions of a customer between provided dates
        List<TransactionsEntity> transactionsEntityList = getAllTransactionsForCustomerBetweenDates(customerId, startDate, endDate);

        List<TransactionsEntity> transactionsEntityList1 = transactionsEntityList.parallelStream().map(transactionsEntity -> {
            Integer points = 0;
            if (transactionsEntity.getTransactionAmount().intValue() >= 50 && transactionsEntity.getTransactionAmount().intValue() < 100) {
                points = transactionsEntity.getTransactionAmount().intValue() - 50;
            } else if (transactionsEntity.getTransactionAmount().intValue() > 100) {
                points = 2 * (transactionsEntity.getTransactionAmount().intValue() - 100) + 50;
            }

            transactionsEntity.setPoints(points);
            return transactionsEntity;

        }).collect(Collectors.toList());

        //Group Months
        Map<String, Integer> pointsByMonth = transactionsEntityList1.stream()
                .collect(Collectors.groupingBy(transactionsEntity -> transactionsEntity.getTransactionDate().getMonth().name(),
                        Collectors.summingInt(TransactionsEntity::getPoints)));

        // Total Reward points for all transactions in all months
        Integer totalRewardPoints = transactionsEntityList1.stream().map(transactionsEntity -> transactionsEntity.getPoints())
                .reduce(0, (integer, integer2) -> integer + integer2);

        // Map Beans
        List<Transactions> list = new ArrayList<>();
        transactionsEntityList1.parallelStream().forEach(transactionsEntity -> {
            Transactions transaction = new Transactions();
            beanMapper.map(transactionsEntity, transaction);
            list.add(transaction);
        });

        // Create Response
        TransactionsList transactionsList = new TransactionsList();
        transactionsList.setTransactionsList(list);
        transactionsList.setTotalRewardPoints(totalRewardPoints);
        transactionsList.setPointsByMonth(pointsByMonth);

        return new ResponseEntity<>(transactionsList, new HttpHeaders(), HttpStatus.OK);
    }

    private List<TransactionsEntity> getAllTransactionsForCustomerBetweenDates(Long customerId, LocalDate sDate, LocalDate eDate) {

        List<TransactionsEntity> transactionsEntityList = null;
        try {
            transactionsEntityList = transactionsRepository
                    .findByCustomersByCustomerId_CustomerIdAndTransactionDateGreaterThanEqualOrderByTransactionDateAsc(customerId, sDate);
        } catch (Exception e) {
            throw new HttpServerErrorException(HttpStatus.INTERNAL_SERVER_ERROR, "An error occurred while fetching data");
        }

        //Iterate over each transaction and filter transactions that are between startDate & endDate
        List<TransactionsEntity> transactionsEntityList1 = transactionsEntityList.stream().filter(transactionsEntity ->
                transactionsEntity.getTransactionDate().isBefore(eDate)
        ).collect(Collectors.toList());

        return transactionsEntityList1;
    }

}
