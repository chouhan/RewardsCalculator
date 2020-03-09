package com.kforce.rewards.mapper;

import com.kforce.rewards.dto.Transactions;
import com.kforce.rewards.entity.TransactionsEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * Created by Rakesh Chouhan.
 */
@Mapper
public interface TransactionMapper extends BaseMapper {

    TransactionMapper MAPPER = Mappers.getMapper(TransactionMapper.class);

    List<Transactions> transactionsDataToTransactionsEntityList(List<TransactionsEntity> transactionsEntityList);

    Transactions transactionsToTransactionsEntity(TransactionsEntity transactionsEntity);

//    List<RewardsDataDAOList> rewardsDataDAOToRewardsDTOList(List<RewardsDataList> rewardsDataLists);
}
