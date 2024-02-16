package com.example.bankingapp.errorandexception;

import static com.example.bankingapp.errorandexception.sendMessage.messageError.INVALID_DATE_FORMAT;

/**
 * @author Jana Metz on 01.02.24
 */
public class ExceptionDateAnomaly extends RuntimeException{
    public ExceptionDateAnomaly(String date) {
        super(date + INVALID_DATE_FORMAT.getMessage());
    }
}
