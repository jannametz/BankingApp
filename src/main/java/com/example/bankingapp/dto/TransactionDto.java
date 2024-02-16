package com.example.bankingapp.dto;

import java.time.LocalDate;
import java.time.LocalTime;
import lombok.Value;

/**
 * @author Jana Metz on 21.01.24
 */


@Value
public class TransactionDto {

    String dateCreation;

    String timeCreation;

    String accountFrom;

    String accountTo;

    String amount;

    String type;
}
