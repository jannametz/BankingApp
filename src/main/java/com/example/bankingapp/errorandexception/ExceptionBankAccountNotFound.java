package com.example.bankingapp.errorandexception;

import com.example.bankingapp.errorandexception.sendMessage.messageError;

import static com.example.bankingapp.errorandexception.sendMessage.messageError.BANK_ACCOUNT_NOT_FOUND;

/**
 * @author Jana Metz on 01.02.24
 */
public class ExceptionBankAccountNotFound  extends RuntimeException {
    public ExceptionBankAccountNotFound(String id) {
        super(messageError.BANK_ACCOUNT_NOT_FOUND.getMessage() + id);
    }
}
