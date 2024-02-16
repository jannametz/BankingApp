package com.example.bankingapp.controller;

import com.example.bankingapp.dto.AccountErrorDto;
import com.example.bankingapp.errorandexception.ExceptionEntityNotFound;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static org.springframework.http.HttpStatus.NOT_FOUND;

/**
 * @author Jana Metz on 30.01.24
 */
@RestControllerAdvice // Zweck --> Exception Handling and etc.
public class HandlerExceptionAndErrorController {

    @ResponseStatus(NOT_FOUND)
    @ExceptionHandler(ExceptionEntityNotFound.class)
public AccountErrorDto entityNotFoundException(ExceptionEntityNotFound exceptionEntityNotFound){
      return buildAccountErrorDto(exceptionEntityNotFound,NOT_FOUND);
    }

    private AccountErrorDto buildAccountErrorDto (Exception exception, HttpStatus status) {
        return AccountErrorDto.builder()
                .status(status)
                .message(exception.getMessage())
                .statusCode(status.value())
                .errors(exception.hashCode())
                .build();
    }
}
