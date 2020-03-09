package com.kforce.rewards.mapper;

import com.kforce.rewards.response.Metadata;
import com.kforce.rewards.response.ResponseApiDTO;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public interface ResponseMapper<T> extends BaseMapper {

    ResponseApiDTO createResponseApiDTO(Metadata metadata, List<T> resultList);
}
