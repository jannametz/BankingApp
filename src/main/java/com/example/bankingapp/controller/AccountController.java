package com.example.bankingapp.controller;

import com.example.bankingapp.dto.AccountRequestDto;
import com.example.bankingapp.dto.AccountResponseDto;
import com.example.bankingapp.service.AccountService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

/**
 * @author Jana Metz on 22.01.24
 */

@RestController
@RequiredArgsConstructor//Notation for Constructor , für Verbindung durch Conscrutor
@RequestMapping("/accounts")//marschrut

public class AccountController {

    private final AccountService accountService;
    private static final String ID = "/{id}";

    @PostMapping()
    @ResponseStatus(CREATED)
    public AccountResponseDto generateAccount(@RequestBody @Valid AccountRequestDto accountRequestDto) {
        return accountService.saveAccount(accountRequestDto);
    }

    @GetMapping()
    @ResponseStatus(OK)
    public List<AccountResponseDto> getAllAccount( @RequestParam(required = false) String dateG,
                                                   @RequestParam(required = false) List<String> city,
                                                   @RequestParam(required = false) String sorting) {
        return accountService.getAllAccount(dateG, city, sorting);
    }

    @GetMapping(ID)
    @ResponseStatus(OK)
    public AccountResponseDto getAccountInfo(@PathVariable String id) {

        return accountService.getAccount(id);
    }

    @PatchMapping(ID)
    @ResponseStatus(OK)
    public AccountResponseDto updateAccount(@PathVariable String id,
                                           @RequestBody AccountRequestDto accountRequestDto) {
        return accountService.editAccount(id, accountRequestDto);
    }

}
