package com.example.bankingapp.dto;

import lombok.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

/**
 * @author Jana Metz on 30.01.24
 */

@ToString
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class AccountErrorDto {

    HttpStatus status;

    String message;

    int statusCode;

    Object errors;



}
