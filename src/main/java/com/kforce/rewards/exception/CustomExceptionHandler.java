/*
 * @CustomExceptionHandler.java
 *
 *  Version 1.0 16/01/2020
 */
package com.kforce.rewards.exception;

import com.kforce.rewards.dao.MetadataDAO;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Iterator;
import java.util.List;

/**
 * The CustomExceptionHandler class defines exception methods to return the custom exception message. The business logic
 * holds the exception details returned from the controller and set the Exception Response message and return back to the
 * controller
 *
 * @author Rakesh Chouhan
 */
@ControllerAdvice
@ResponseBody
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

    private MetadataDAO metadataDAO;

    /**
     * @param ex
     * @param request
     * @return
     */
    @ExceptionHandler(Exception.class)
    public final ResponseEntity<Exception> handleNotFoundException(Exception ex, WebRequest request) {
        ErrorResponseDetails exceptionResponse = new ErrorResponseDetails(HttpStatus.INTERNAL_SERVER_ERROR.toString(), ex.getMessage());
        return new ResponseEntity(exceptionResponse, HttpStatus.NOT_ACCEPTABLE);
    }

    /**
     * Error Handling for @Valid. Non Empty values in RequestBody.
     *
     * @param ex
     * @param
     * @return
     */
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders httpHeaders,
                                                                  HttpStatus httpStatus, WebRequest webRequest) {
        List<FieldError> fieldErrors = ex.getBindingResult().getFieldErrors();
        StringBuilder sb = new StringBuilder();
        Iterator<?> it = fieldErrors.iterator();
        String SEPARATOR = "";
        while (it.hasNext()) {
            sb.append(SEPARATOR);
            sb.append(((FieldError) it.next()).getDefaultMessage());
            SEPARATOR = ", ";
        }
        ErrorResponseDetails exceptionResponse = new ErrorResponseDetails(Integer.toString(httpStatus.value()), sb.append(" is required.").toString());
        return new ResponseEntity<Object>(exceptionResponse, HttpStatus.BAD_REQUEST);
    }
}
