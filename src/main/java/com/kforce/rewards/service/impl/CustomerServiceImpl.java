package com.kforce.rewards.service.impl;

import com.kforce.rewards.dto.Customers;
import com.kforce.rewards.entity.CustomersEntity;
import com.kforce.rewards.repository.CustomersRepository;
import com.kforce.rewards.response.CustomerResponseHandler;
import com.kforce.rewards.response.ResponseApiDTO;
import com.kforce.rewards.service.CustomerService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class CustomerServiceImpl implements CustomerService {

    private static final Logger LOGGER = LogManager.getLogger(CustomerServiceImpl.class);

    @Autowired
    CustomersRepository customersRepository;

    @Autowired
    CustomerResponseHandler customerResponseHandler;

    @Override
    @Cacheable("allCustomers")
    public ResponseApiDTO<Customers> findAllCustomers() {
        LOGGER.info("Inside " + this.getClass() + " findAllCustomers method");

        List<CustomersEntity> customersList = customersRepository.findAll();
        return customerResponseHandler.handleApiResponse(customersList, customersList.size());
    }

    @Override
    @Cacheable("customersByUserName")
    public ResponseApiDTO<Customers> findCustomerByUserName(String userName) {
        LOGGER.info("Inside " + this.getClass() + " findCustomerByUserName method");

        CustomersEntity customer = customersRepository.findCustomersEntityByUserName(userName);
        List<CustomersEntity> customersList = new ArrayList<>();
        customersList.add(customer);
        return customerResponseHandler.handleApiResponse(customersList, customersList.size());
    }

    @Override
    @Cacheable("customersByCustomerId")
    public ResponseApiDTO<Customers> findCustomerByCustomerId(Integer customerId) {
        LOGGER.info("Inside " + this.getClass() + " findCustomerByUserName method");

        Optional<CustomersEntity> customer = customersRepository.findById(customerId);
        List<CustomersEntity> customersList = new ArrayList<>();
        if (customer != null) {
            customersList.add(customer.get());
        }
        return customerResponseHandler.handleApiResponse(customersList, customersList.size());
    }
}
