package com.caliber.flatbudget.dtos.transaction;

import com.caliber.flatbudget.models.Transaction;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
public interface TransactionMapper {
    TransactionMapper INSTANCE = Mappers.getMapper(TransactionMapper.class);

    TransactionDto transactionToTransactionDto(Transaction transaction);

    Transaction transactionDtoToTransaction(TransactionDto transactionDto);
}
