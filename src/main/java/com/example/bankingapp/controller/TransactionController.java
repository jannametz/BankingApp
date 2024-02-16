package com.example.bankingapp.controller;

import com.example.bankingapp.dto.AccountRequestDto;
import com.example.bankingapp.dto.AccountResponseDto;
import com.example.bankingapp.dto.TransactionDto;
import com.example.bankingapp.service.AccountService;
import com.example.bankingapp.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

/**
 * @author Jana Metz on 22.01.24
 */
@RestController
@RequiredArgsConstructor

public class TransactionController {

    private final TransactionService transactionService;

    private final AccountService accountService;

    @ResponseStatus(OK)

    @GetMapping("/transactions")
    public List<TransactionDto> getAllTransactions(@RequestParam(required = false) String date,
                                                   @RequestParam(required = false) String type,
                                                   @RequestParam(required = false) String sort) {
        return transactionService.getAllTransaction(date, type, sort);
    }

    @ResponseStatus(OK)
    @GetMapping("/transactions/{id}")

    public TransactionDto getTransaction(@PathVariable Long id) {
        return transactionService.getTransaction(id);
    }

    @ResponseStatus(OK)
    @PutMapping("/accounts")

    public void putTransaction(@RequestParam String from,
                               @RequestParam String to,
                               @RequestParam Double amount) {
        accountService.putTransaction(from, to, amount);
    }
}
