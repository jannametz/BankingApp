package com.example.bankingapp.dto;

import lombok.ToString;
import lombok.Value;

/**
 * @author Jana Metz on 21.01.24
 */

@Value()
@ToString
public class AccountRequestDto {

        String email;

        String fName;

        String lName;

        String country;

        String city;
    }
