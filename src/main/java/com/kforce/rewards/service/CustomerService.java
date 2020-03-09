package com.kforce.rewards.service;

import com.kforce.rewards.response.ResponseApiDTO;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public interface CustomerService {

    ResponseApiDTO findAllCustomers() throws IOException;

    ResponseApiDTO findCustomerByUserName(String userName) throws IOException;

    ResponseApiDTO findCustomerByCustomerId(Integer customerId) throws IOException;
}
