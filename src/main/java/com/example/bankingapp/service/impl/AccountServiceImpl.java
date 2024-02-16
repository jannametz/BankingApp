package com.example.bankingapp.service.impl;

import com.example.bankingapp.dto.AccountRequestDto;
import com.example.bankingapp.dto.AccountResponseDto;
import com.example.bankingapp.entity.Account;
import com.example.bankingapp.entity.Transaction;
import com.example.bankingapp.mapper.AccountMapper;
import com.example.bankingapp.repository.AccountRepository;
import com.example.bankingapp.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

import static com.example.bankingapp.service.util.PullRequestsChecker.*;
import static java.time.LocalDate.parse;

/**
 * @author Jana Metz on 22.01.24
 */
@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

    private final AccountMapper accountMapper;

    private final AccountRepository accountRepository;

    private final TransactionServiceImpl transactionServiceImpl;
    @Override
    @Transactional
    public AccountResponseDto saveAccount(AccountRequestDto accountRequestDto) {
        return accountMapper.toResponseDto(accountRepository.save(accountMapper.toEntity(accountRequestDto)));
    }

    @Override
    @Transactional
    public AccountResponseDto editAccount(String id, AccountRequestDto account) {
        Account patchedAccount = accountRepository.findById(id);
        checkingAccount(patchedAccount, id);

        if (account.getCity() != null && !account.getCity().isEmpty())
            patchedAccount.setCity(account.getCity());

        if (account.getCountry() != null && !account.getCountry().isEmpty())
            patchedAccount.setCountry(account.getCountry());

        if (account.getEmail() != null && !account.getEmail().isEmpty())
            patchedAccount.setEmail(account.getEmail());

        if (account.getFName() != null && !account.getFName().isEmpty())
            patchedAccount.setFName(account.getFName());

        if (account.getLName() != null && !account.getLName().isEmpty())
            patchedAccount.setLName(account.getLName());

       /* accountRepository.updateAccountById(
                patchedAccount.getEmail(),
                patchedAccount.getFName(),
                patchedAccount.getLName(),
                patchedAccount.getCountry(),
                patchedAccount.getCity(),
                id
        );*/

        return getAccount(id);
    }

    @Override
    public AccountResponseDto getAccount(String id) {
        Account account = accountRepository.findById(id);
        checkingAccount(account, id);

        return accountMapper.toResponseDto(account);
    }

    @Override
    public List<AccountResponseDto> getAllAccount(String date, List<String> cities, String sort) {
        checkingDate(date);
        return accountMapper.accountsToAccountResponseDto(getAccountsWithParameters(date, cities, sort));
    }

    @Override
    @Transactional
    public void putTransaction(String fromId,
                               String toId,
                               Double amount) {
        Account fromAccount = accountRepository.findById(fromId);
        Account toAccount = accountRepository.findById(toId);
        checkingTansactionConectPosobbility(fromId, toId, amount, fromAccount, toAccount);

        Transaction transaction = transactionServiceImpl.createTransaction(fromId, toId, amount);

        if (!fromId.equals(toId)) {
            fromAccount.addTransaction(transaction);
            fromAccount.setMoneyAmount(fromAccount.getMoneyAmount().add(BigDecimal.valueOf(-amount)));
        //    accountRepository.updateAmountOfMoneyById(fromAccount.getMoneyAmount(), fromId);
        }

        toAccount.addTransaction(transaction);
        toAccount.setMoneyAmount(toAccount.getMoneyAmount().add(BigDecimal.valueOf(amount)));
       // accountRepository.updateAmountOfMoneyById(toAccount.getMoneyAmount(), toId);
    }

    private List<Account> getAccountsWithParameters(String date, List<String> cities, String sort) {
        boolean dateIsNotNullOrEmpty = date != null && !date.isBlank();
        boolean cityIsNotNullOrEmpty = cities != null && !cities.isEmpty();
        boolean dateAndCityAreNotNullOrEmpty = dateIsNotNullOrEmpty && cityIsNotNullOrEmpty;

        if (sort != null && !sort.isBlank()) {
            if (sort.equalsIgnoreCase("dateCreation")) {
                return returnAccountsOrderedByDateAsc(date, cities, dateIsNotNullOrEmpty, cityIsNotNullOrEmpty, dateAndCityAreNotNullOrEmpty);

            } else if (sort.equalsIgnoreCase("-dateCreation")) {
                return returnAccountsOrderedByDateDesc(date, cities, dateIsNotNullOrEmpty, cityIsNotNullOrEmpty, dateAndCityAreNotNullOrEmpty);

            } else
                return returnAccountsWithoutOrder(date, cities, dateIsNotNullOrEmpty, cityIsNotNullOrEmpty, dateAndCityAreNotNullOrEmpty);

        } else
            return returnAccountsWithoutOrder(date, cities, dateIsNotNullOrEmpty, cityIsNotNullOrEmpty, dateAndCityAreNotNullOrEmpty);
    }

    private List<Account> returnAccountsWithoutOrder(String date,
                                                     List<String> cities,
                                                     boolean dateIsNotNullOrEmpty,
                                                     boolean cityIsNotNullOrEmpty,
                                                     boolean dateAndCityAreNotNullOrEmpty) {
        if (dateAndCityAreNotNullOrEmpty) {
            //return all accounts with given CITY and DATE
            return accountRepository.findByCityInAndCreationDate(cities, parse(date));
        } else if (cityIsNotNullOrEmpty) {
            //return all accounts with given CITY
            return accountRepository.findByCityIn(cities);
        } else if (dateIsNotNullOrEmpty) {
            //return all accounts with given DATE
            return accountRepository.findByCreationDate(parse(date));
        } else
            //return all accounts
            return accountRepository.findAll();
    }

    private List<Account> returnAccountsOrderedByDateDesc(String date,
                                                          List<String> cities,
                                                          boolean dateIsNotNullOrEmpty,
                                                          boolean cityIsNotNullOrEmpty,
                                                          boolean dateAndCityAreNotNullOrEmpty) {
        if (dateAndCityAreNotNullOrEmpty) {
            //return all accounts with given CITY and DATE ordered DESCENDING by DATE
            return accountRepository.findByCityInAndCreationDateOrderByCreationDateDesc(cities, parse(date));
        } else if (cityIsNotNullOrEmpty) {
            //return all accounts with given CITY ordered DESCENDING by DATE
            return accountRepository.findByCityInOrderByCreationDateDesc(cities);
        } else if (dateIsNotNullOrEmpty) {
            //return all accounts with given DATE ordered DESCENDING by DATE
            return accountRepository.findByCreationDateOrderByCreationDateDesc(parse(date));
            //return all accounts ordered DESCENDING by DATE
        } else return accountRepository.findAllOrderedDesc();
    }

    private List<Account> returnAccountsOrderedByDateAsc(String date,
                                                         List<String> cities,
                                                         boolean dateIsNotNullOrEmpty,
                                                         boolean cityIsNotNullOrEmpty,
                                                         boolean dateAndCityAreNotNullOrEmpty) {
        if (dateAndCityAreNotNullOrEmpty) {
            //return all accounts with given CITY and DATE ordered ASCENDING by DATE
            return accountRepository.findByCityInAndCreationDateOrderByCreationDateAsc(cities, parse(date));
        } else if (cityIsNotNullOrEmpty) {
            //return all accounts with given CITY ordered ASCENDING by DATE
            return accountRepository.findByCityInOrderByCreationDateAsc(cities);
        } else if (dateIsNotNullOrEmpty) {
            //return all accounts with given DATE ordered ASCENDING by DATE
            return accountRepository.findByCreationDateOrderByCreationDateAsc(parse(date));
            //return all accounts ordered ASCENDING by DATE
        } else return accountRepository.findAllOrderedAsc();
    }

}
