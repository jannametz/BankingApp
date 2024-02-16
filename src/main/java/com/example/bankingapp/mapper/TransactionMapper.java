package com.example.bankingapp.mapper;

import  com.example.bankingapp.dto.TransactionDto;
import  com.example.bankingapp.entity.Transaction;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * @author Jana  Metz on 22.01.24
 */
@Mapper(componentModel = "spring")
public interface TransactionMapper {
    TransactionDto toDTO(Transaction transaction);

    List<TransactionDto> transactionsToTransactionDto(List<Transaction> transactions);

    static String getId(Transaction transaction){

        return transaction.getId().toString();
    }
}

