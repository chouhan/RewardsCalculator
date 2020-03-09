package com.kforce.rewards.exception;

import com.kforce.rewards.response.Metadata;
import lombok.Getter;

@Getter
public class ApplicationException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    private final String applicationCode;
    private final transient Metadata metadata;

    /**
     * @param applicationCode
     * @param metadata
     */
    public ApplicationException(String applicationCode, Metadata metadata) {
        super();
        this.applicationCode = applicationCode;
        this.metadata = metadata;
    }

}
