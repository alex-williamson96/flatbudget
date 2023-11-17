package com.caliber.flatbudget.dtos.Transaction;

import com.caliber.flatbudget.models.Transaction;
import org.springframework.stereotype.Component;

@Component
public class TransactionMapper {
    public static TransactionDto toDto(Transaction transaction) {
        return TransactionDto.builder()
                .id(transaction.getId())
                .name(transaction.getName())
                .transactionDate(transaction.getTransactionDate())
                .note(transaction.getNote())
                .dollar(transaction.getDollar())
                .cents(transaction.getCents())
                .isOutflow(transaction.getIsOutflow())
                .isPending(transaction.getIsPending())
                .build();
    }
}
