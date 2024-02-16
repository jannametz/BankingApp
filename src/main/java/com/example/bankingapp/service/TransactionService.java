package com.example.bankingapp.service;

import com.example.bankingapp.dto.TransactionDto;

import java.util.List;

/**
 * @author Jana Metz on 22.01.24
 */
public interface TransactionService {
    TransactionDto getTransaction(Long id);

    List<TransactionDto> getAllTransaction(String date, String type, String sort);
}
