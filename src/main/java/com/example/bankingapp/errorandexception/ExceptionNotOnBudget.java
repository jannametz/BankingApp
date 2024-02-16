package com.example.bankingapp.errorandexception;

import static com.example.bankingapp.errorandexception.sendMessage.messageError.NOT_ON_BUDGET;

/**
 * @author Jana Metz on 02.02.24
 */
public class ExceptionNotOnBudget extends RuntimeException {

public ExceptionNotOnBudget(String message){
    super(NOT_ON_BUDGET.getMessage() + message);
}
}
