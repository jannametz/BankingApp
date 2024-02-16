package com.example.bankingapp.errorandexception;

/**
 * @author Jana Metz on 30.01.24
 */
public class ExceptionEntityNotFound extends RuntimeException {

   public ExceptionEntityNotFound(String message) {
       super(message);
   }
}
