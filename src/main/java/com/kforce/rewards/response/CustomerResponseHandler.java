package com.kforce.rewards.response;

import com.kforce.rewards.dto.Customers;
import com.kforce.rewards.entity.CustomersEntity;
import com.kforce.rewards.mapper.CustomerMapper;
import com.kforce.rewards.mapper.ResponseMapperImpl;
import com.kforce.rewards.util.DomainFilter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by Rakesh Chouhan
 */
@Component
@Setter
public class CustomerResponseHandler extends ResponseHandler {

    @Autowired
    ResponseMapperImpl responseMapperImpl;

    public ResponseApiDTO<Customers> handleApiResponse(List<CustomersEntity> data, long dataSize) {
        getResponseValidator().validateEmptyResponse(data);
        Metadata metadata = initiaizedResponseData(null, DomainFilter.customersFilter, String.valueOf(dataSize), "Success", "OK");
        return responseMapperImpl.createResponseApiDTO(metadata, CustomerMapper.MAPPER.customersDataToCustomersEntityList(data));
    }

}
