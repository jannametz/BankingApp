package com.example.bankingapp.errorandexception;

import static com.example.bankingapp.errorandexception.sendMessage.messageError.STATUS_INVALID_TYPE_TRANSACTION;

/**
 * @author Jana Metz on 02.02.24
 */
public class ExceptionStatusInvalidTypeTransaction extends RuntimeException{
    public ExceptionStatusInvalidTypeTransaction(String type) {
        super(type + STATUS_INVALID_TYPE_TRANSACTION.getMessage());
    }
}
