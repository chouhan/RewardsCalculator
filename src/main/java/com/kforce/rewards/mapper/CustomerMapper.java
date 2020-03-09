package com.kforce.rewards.mapper;

import com.kforce.rewards.dto.Customers;
import com.kforce.rewards.entity.CustomersEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * Created by Rakesh Chouhan.
 */
@Mapper
public interface CustomerMapper extends BaseMapper {

    CustomerMapper MAPPER = Mappers.getMapper(CustomerMapper.class);

    List<Customers> customersDataToCustomersEntityList(List<CustomersEntity> customersEntityList);

//    List<Optional<Customers>> customersDataOptionalListToCustomersEntityList(List<CustomersEntity> customersEntityList);

//    Optional<Customers> customersDataOptionalToCustomersEntity(Optional<CustomersEntity> value);

    @Mapping(target = "cellPhoneNumber", source = "customersEntity.telephoneNumber")
    Customers customersToCustomersEntity(CustomersEntity customersEntity);
}
