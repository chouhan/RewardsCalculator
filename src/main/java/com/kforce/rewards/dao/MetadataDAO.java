/**
 *
 */
package com.kforce.rewards.dao;

import com.kforce.rewards.response.Metadata;
import com.kforce.rewards.util.StatusMessage;
import org.springframework.http.HttpStatus;

public interface MetadataDAO {

    /**
     * This method captures all metadata parameters.
     * Some of these parameters can be cached like the version, status codes and status strings
     * @param statusMessage
     * @param resultCount
     * @param httpStatus
     */
    default Metadata setMetadataModel(StatusMessage statusMessage, String resultCount, HttpStatus httpStatus) {
        Metadata metadata = new Metadata();
        if ("success".equals(statusMessage.toString())) {
            metadata = new Metadata();
            metadata.setVersion("1.0");
            metadata.setStatus("success!");
            metadata.setHTTP_status_code(httpStatus.toString());
            metadata.setResult_count(resultCount);
        }
        if ("error".equals(statusMessage.toString())) {
            metadata = new Metadata();
            metadata.setVersion("1.0");
            metadata.setStatus("error!");
            metadata.setHTTP_status_code(httpStatus.toString());
            metadata.setResult_count(resultCount);
        }
        return metadata;
    }
}
