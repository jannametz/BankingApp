package com.example.bankingapp.service;

import com.example.bankingapp.dto.AccountRequestDto;
import com.example.bankingapp.dto.AccountResponseDto;

import java.util.List;

/**
 * @author Jana Metz on 22.01.24
 */
public interface AccountService {
    AccountResponseDto saveAccount(AccountRequestDto accountRequestDto);

    AccountResponseDto editAccount(String id, AccountRequestDto accountRequestDto);

    AccountResponseDto getAccount(String id);

    List<AccountResponseDto> getAllAccount(String date, List<String> cities, String sort);

    void putTransaction(String from, String to, Double amount);
}
