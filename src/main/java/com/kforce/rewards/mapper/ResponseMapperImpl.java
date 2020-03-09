package com.kforce.rewards.mapper;

import com.kforce.rewards.response.Metadata;
import com.kforce.rewards.response.ResponseApiDTO;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ResponseMapperImpl<T> implements ResponseMapper<T> {

    @Override
    public ResponseApiDTO<T> createResponseApiDTO(Metadata metadata, List<T> resultList) {
        ResponseApiDTO<T> responseApiDTO = new ResponseApiDTO<T>();
        responseApiDTO.setMetadata(metadata);
        responseApiDTO.setResult(resultList);
        return responseApiDTO;
    }

    @Override
    public Metadata metadataDTOToMetadata(Metadata metadata) {
        return null;
    }
}
