package com.kforce.rewards.service.impl;

import com.kforce.rewards.dto.Customers;
import com.kforce.rewards.dto.PointsObject;
import com.kforce.rewards.dto.Transactions;
import com.kforce.rewards.entity.CustomersEntity;
import com.kforce.rewards.entity.TransactionsEntity;
import com.kforce.rewards.repository.CustomersRepository;
import com.kforce.rewards.repository.TransactionsRepository;
import com.kforce.rewards.response.CustomerResponseHandler;
import com.kforce.rewards.response.ResponseApiDTO;
import com.kforce.rewards.response.TransactionResponseHandler;
import com.kforce.rewards.service.TransactionService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;


@Component
public class TransactionServiceImpl implements TransactionService {

    private static final Logger LOGGER = LogManager.getLogger(TransactionServiceImpl.class);

    @Autowired
    CustomersRepository customersRepository;

    @Autowired
    TransactionsRepository transactionsRepository;

    @Autowired
    TransactionResponseHandler transactionResponseHandler;

    @Autowired
    CustomerResponseHandler customerResponseHandler;

    @Override
    @Cacheable(value = "allTransactions")
    public ResponseApiDTO<Transactions> findAllTransactions() {
        LOGGER.info("Inside " + this.getClass() + " findAllTransactions method");
        List<TransactionsEntity> transactionsList = transactionsRepository.findAll();
        return transactionResponseHandler.handleApiResponse(transactionsList, transactionsList.size());
    }

    @Override
    @Cacheable(value = "transactionsByName")
    public ResponseApiDTO<Transactions> findTransactionByTransactionName(String transactionName) {
        LOGGER.info("Inside " + this.getClass() + " findTransactionByTransactionName method");
        TransactionsEntity transactionsEntity = transactionsRepository.findTransactionsEntityByTransactionName(transactionName);
        List<TransactionsEntity> transactionsEntityList = new ArrayList<>();
        transactionsEntityList.add(transactionsEntity);
        return transactionResponseHandler.handleApiResponse(transactionsEntityList, transactionsEntityList.size());
    }

    @Override
    @Cacheable(value = "transactionsById")
    public ResponseApiDTO<Transactions> findTransactionByTransactionId(Integer transactionId) {
        LOGGER.info("Inside " + this.getClass() + " findTransactionByTransactionId method");
        Optional<TransactionsEntity> transaction = transactionsRepository.findById(transactionId);
        List<TransactionsEntity> transactionsEntityList = new ArrayList<>();
        if (transaction != null) {
            transactionsEntityList.add(transaction.get());
        }
        return transactionResponseHandler.handleApiResponse(transactionsEntityList, transactionsEntityList.size());
    }

    @Override
    @Cacheable(value = "transactionsByCustomerId")
    public ResponseApiDTO<Transactions> findAllTransactionsByCustomerId(Integer customerId) {
        LOGGER.info("Inside " + this.getClass() + " findAllTransactionsByCustomerId method");
        List<TransactionsEntity> transactionsEntityList = transactionsRepository.findByCustomersByCustomerId_CustomerId(customerId);
        return transactionResponseHandler.handleApiResponse(transactionsEntityList, transactionsEntityList.size());
    }

    @Override
    @Cacheable(value = "customerByTransactionId")
    public ResponseApiDTO<Customers> findCustomerByTransactionId(Integer transactionId) {
        LOGGER.info("Inside " + this.getClass() + " findCustomerByTransactionId method");
        Optional<TransactionsEntity> transactionsEntity = transactionsRepository.findById(transactionId);
        Optional<CustomersEntity> customer = null;
        if (transactionsEntity != null) {
            customer = customersRepository.findById(transactionsEntity.get().getCustomersByCustomerId().getCustomerId());
        }
        List<CustomersEntity> customersEntityList = new ArrayList<>();
        if (customer != null) {
            customersEntityList.add(customer.get());
        }
        return customerResponseHandler.handleApiResponse(customersEntityList, customersEntityList.size());
    }


    @Override
    @Cacheable(value = "transactionByCustomerIdAndTransactionId")
    public ResponseApiDTO<Transactions> findTransactionByCustomerIdAndTransactionId(Integer customerId, Integer transactionId) {
        LOGGER.info("Inside " + this.getClass() + " findTransactionByCustomerIdAndTransactionId method");
        Optional<TransactionsEntity> transaction = transactionsRepository.findByCustomersByCustomerId_CustomerIdAndTransactionId(customerId, transactionId);
        List<TransactionsEntity> transactionsEntityList = new ArrayList<>();
        if (transaction != null) {
            transactionsEntityList.add(transaction.get());
        }
        return transactionResponseHandler.handleApiResponse(transactionsEntityList, transactionsEntityList.size());
    }


    @Override
    @Cacheable(value = "transactionsForCustomersBetweenDates")
    public ResponseApiDTO<Transactions> findAllTransactionsForCustomerBetweenDates(Integer customerId, String startDate, String endDate) {
        LOGGER.info("Inside " + this.getClass() + " findAllTransactionsForCustomerBetweenDates method");
        List<TransactionsEntity> list = getAllTransactionsForCustomerBetweenDates(customerId, startDate, endDate);
        return transactionResponseHandler.handleApiResponse(list, list.size());
    }

    @Override
    @Cacheable(value = "rewardPointsCalculator")
    public ResponseApiDTO<Transactions> findAllRewardPointsForCustomerBetweenDates(Integer customerId, String startDate, String endDate) {
        LOGGER.info("Inside " + this.getClass() + " findAllRewardPointsForCustomerBetweenDates method");

        List<TransactionsEntity> transactionsEntityList = getAllTransactionsForCustomerBetweenDates(customerId, startDate, endDate);
        List<PointsObject> pointsList = new ArrayList();

        List<TransactionsEntity> transactionsEntityList1 = transactionsEntityList.stream().map(transactionsEntity -> {
            Integer points = 0;
            if (transactionsEntity.getTransactionAmount().intValue() >= 50 && transactionsEntity.getTransactionAmount().intValue() < 100) {
                points = transactionsEntity.getTransactionAmount().intValue() - 50;
            } else if (transactionsEntity.getTransactionAmount().intValue() > 100) {
                points = 2 * (transactionsEntity.getTransactionAmount().intValue() - 100) + 50;
            }

            PointsObject pointsObject = new PointsObject();
            pointsObject.setMonth(transactionsEntity.getTransactionDate().getMonth().name());
            pointsObject.setPoints(points);
            pointsList.add(pointsObject);

            transactionsEntity.setPoints(points);
            return transactionsEntity;

        }).collect(Collectors.toList());

        Integer totalRewardPoints = transactionsEntityList1.stream().map(transactionsEntity -> transactionsEntity.getPoints())
                .reduce(0, (integer, integer2) -> integer + integer2);

        // Group Months
        Map<String, Integer> pointsByMonth = pointsList.stream()
                .collect(Collectors.groupingBy(PointsObject::getMonth,
                        Collectors.summingInt(PointsObject::getPoints)));

        ResponseApiDTO<Transactions> responseApiDTO = transactionResponseHandler.handleApiResponse(transactionsEntityList1, transactionsEntityList1.size());
        Object[] objArray = {"Total Reward Points: " + totalRewardPoints, "Points By Month: " + pointsByMonth};
        responseApiDTO.setAdditionalData(objArray);
        return responseApiDTO;
    }

    public List<TransactionsEntity> getAllTransactionsForCustomerBetweenDates(Integer customerId, String startDate, String endDate) {
        LocalDate sDate = LocalDate.parse(startDate, DateTimeFormatter.ofPattern("MM-dd-yyyy"));
        LocalDate eDate = StringUtils.isEmpty(endDate) ? LocalDate.now() : LocalDate.parse(endDate, DateTimeFormatter.ofPattern("MM-dd-yyyy"));

        if (eDate.isBefore(sDate)) {
            throw new IllegalArgumentException("Start date cannot be greater than end date");
        }
        List<TransactionsEntity> transactionsEntityList = transactionsRepository
                .findByCustomersByCustomerId_CustomerIdAndTransactionDateGreaterThanEqualOrderByTransactionDateAsc(customerId, sDate);

        //Iterate over each transaction and filter transactions that are between startDate & endDate
        List<TransactionsEntity> transactionsEntityList1 = transactionsEntityList.stream().filter(transactionsEntity ->
                transactionsEntity.getTransactionDate().isBefore(eDate)
        ).collect(Collectors.toList());

        return transactionsEntityList1;
    }

}
