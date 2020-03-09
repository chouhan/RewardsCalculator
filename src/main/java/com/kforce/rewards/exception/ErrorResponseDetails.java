/*
 * @ErrorResponseDetails.java
 *
 *  Version 1.0 16/01/2020
 */
package com.kforce.rewards.exception;

import lombok.Getter;
import lombok.Setter;

/**
 * The ErrorResponseDetails class holds the attributes representing the response code and response message. These attributes are used
 * to send the exception response when an unexpected error occurred during processing of request
 *
 * @author Surender Reddy
 */
@Getter
@Setter
public class ErrorResponseDetails {

    private String responseCode;
    private String responseMessage;

    public ErrorResponseDetails(String errorCode, String errorMessage) {
        this.responseCode = errorCode;
        this.responseMessage = errorMessage;
    }
}
