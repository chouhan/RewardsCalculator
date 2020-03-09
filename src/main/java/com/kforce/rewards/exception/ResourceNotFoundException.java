package com.kforce.rewards.exception;


import com.kforce.rewards.response.Metadata;

public class ResourceNotFoundException extends ApplicationException {

    private static final long serialVersionUID = 1L;

    /**
     * @param applicationCode
     * @param metadata
     */
    public ResourceNotFoundException(String applicationCode, Metadata metadata) {
        super(applicationCode, metadata);
    }

}
