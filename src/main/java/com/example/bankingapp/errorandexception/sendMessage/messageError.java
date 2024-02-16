package com.example.bankingapp.errorandexception.sendMessage;

import lombok.AllArgsConstructor;
import lombok.Getter;

import static com.example.bankingapp.entity.enums.TransactionType.*;

/**
 * @author Jana Metz on 30.01.24
 */
@Getter
@AllArgsConstructor
public enum messageError {
    NOT_ON_BUDGET("You are not on budget.  ID = "),
    BANK_ACCOUNT_NOT_FOUND("Bank account not found. Account ID = "),
    INVALID_AMOUNT("Invalid amount. Amount shouldn't be 0. "),
    NEGATIVE_TRANSFER_AMOUNT("Transfer amount can't be negative. "),
    TRANSACTION_NOT_FOUND("Transaction not found."),
    STATUS_INVALID_TYPE_TRANSACTION ("Invalid transaction type. Supported types: " + WITHDRAW + ", " + ATM + ", " + TRANSFER),
    INVALID_DATE_FORMAT("Invalid date format. Please use the following format: yyyy-MM-dd");


private final String message;


}

