package com.kforce.rewards.response;

import com.kforce.rewards.dto.Transactions;
import com.kforce.rewards.entity.TransactionsEntity;
import com.kforce.rewards.mapper.ResponseMapperImpl;
import com.kforce.rewards.mapper.TransactionMapper;
import com.kforce.rewards.util.DomainFilter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by ChouhR01 on 12/6/2018.
 */
@Component
@Setter
public class TransactionResponseHandler extends ResponseHandler {

    @Autowired
    ResponseMapperImpl responseMapperImpl;

    public ResponseApiDTO<Transactions> handleApiResponse(List<TransactionsEntity> data, long dataSize) {
        getResponseValidator().validateEmptyResponse(data);
        Metadata metadata = initiaizedResponseData(null, DomainFilter.transactionsFilter, String.valueOf(dataSize), "Success", "OK");
        return responseMapperImpl.createResponseApiDTO(metadata, TransactionMapper.MAPPER.transactionsDataToTransactionsEntityList(data));
    }

}
