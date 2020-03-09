package com.kforce.rewards.dto;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
//@JsonInclude(Include.NON_EMPTY)
@JsonFilter("customersFilter")
public class Customers {

    @JsonProperty("customer_id")
    private Integer customerId;

    @JsonProperty("user_name")
    private String userName;

    @JsonProperty("first_name")
    private String firstName;

    @JsonProperty("last_name")
    private String lastName;

    @JsonProperty("status")
    private String status;

    @JsonProperty("email_address")
    private String emailAddress;

    @JsonProperty("telephone_number")
    private String cellPhoneNumber;

}
