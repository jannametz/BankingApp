package com.example.bankingapp.mapper;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import com.example.bankingapp.dto.AccountRequestDto;
import com.example.bankingapp.dto.AccountResponseDto;
import com.example.bankingapp.entity.Account;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import static org.mapstruct.InjectionStrategy.CONSTRUCTOR;


/**
 * @author Jana Metz on 22.01.24
 */
@Mapper(componentModel = "spring",
        uses = {TransactionMapper.class},
        injectionStrategy = CONSTRUCTOR,
        imports = {LocalDate.class, BigDecimal.class})

public interface AccountMapper {
    AccountResponseDto toResponseDto(Account account);

    @Mapping(target = "creationDate", expression = "java(LocalDate.now())")
    @Mapping(target = "moneyAmount", expression = "java(BigDecimal.ZERO)")
    Account toEntity(AccountRequestDto accountRequestDto);

    List<AccountResponseDto> accountsToAccountResponseDto(List<Account> accounts);
}
