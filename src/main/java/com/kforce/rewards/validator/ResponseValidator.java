package com.kforce.rewards.validator;

import com.kforce.rewards.dao.MetadataDAO;
import com.kforce.rewards.exception.ResourceNotFoundException;
import com.kforce.rewards.util.StatusMessage;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

@Component
@Getter
@Setter
public class ResponseValidator {

    private MetadataDAO metadataDAO;

    public boolean validateEmptyResponse(Object response) throws ResourceNotFoundException {
        if (response instanceof List<?> && (Collections.singleton(response).isEmpty())) {
            throw new ResourceNotFoundException("API", metadataDAO.setMetadataModel(StatusMessage.error, "0", HttpStatus.NOT_FOUND));
        } else if (response == null) {
            throw new ResourceNotFoundException("API", metadataDAO.setMetadataModel(StatusMessage.error, "0", HttpStatus.NOT_FOUND));
        }
        return true;
    }

}
