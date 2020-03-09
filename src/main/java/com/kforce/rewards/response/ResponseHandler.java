package com.kforce.rewards.response;

import com.kforce.rewards.config.JacksonConfiguration;
import com.kforce.rewards.util.DomainFilter;
import com.kforce.rewards.validator.ResponseValidator;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;


@Component
public class ResponseHandler {

    @Autowired
    private JacksonConfiguration jacksonConfiguration;

    @Autowired
    @Getter
    @Setter
    private ResponseValidator responseValidator;

    /**
     * @param fields
     * @param domainFilter
     * @param resultCount
     * @param status
     * @param httpStatusCode
     * @return
     * @throws IOException
     */
    protected Metadata initiaizedResponseData(String fields, DomainFilter domainFilter,
                                              String resultCount, String status, String httpStatusCode) {
        if (domainFilter != null) {
            jacksonConfiguration.mapBeanFilterAndSerialize(fields, domainFilter);
        }
        Metadata metadata = new Metadata();
        metadata.setResult_count(resultCount);
        metadata.setVersion("1.0.0");
        metadata.setStatus(status);
        metadata.setHTTP_status_code(httpStatusCode);
        return metadata;
    }

}
