package com.example.bankingapp.errorandexception;

import com.example.bankingapp.errorandexception.ExceptionEntityNotFound;

/**
 * @author Jana Metz on 30.01.24
 */
public class ExceptionTransactionNotFound  extends ExceptionEntityNotFound {
   public ExceptionTransactionNotFound(String message){
       super(message);
   }
}
