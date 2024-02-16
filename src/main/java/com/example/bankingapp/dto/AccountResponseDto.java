package com.example.bankingapp.dto;
import lombok.Value;

import java.util.Set;

/**
 * @author Jana Metz on 21.01.24
 */
@Value
public class AccountResponseDto {

        String id;

        String email;

        String creationDate;

        String fName;

        String lName;

        String country;

        String city;

        String moneyAmount;

        Set<String> transactions;

}
