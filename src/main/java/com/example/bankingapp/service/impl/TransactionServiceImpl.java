package com.example.bankingapp.service.impl;

import com.example.bankingapp.dto.TransactionDto;
import com.example.bankingapp.entity.Transaction;
import com.example.bankingapp.entity.enums.TransactionType;
import com.example.bankingapp.errorandexception.ExceptionTransactionNotFound;
import com.example.bankingapp.mapper.TransactionMapper;
import com.example.bankingapp.repository.TransactionRepository;
import com.example.bankingapp.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Objects;

import static com.example.bankingapp.entity.enums.TransactionType.*;
import static com.example.bankingapp.service.util.PullRequestsChecker.checkingDate;
import static com.example.bankingapp.service.util.PullRequestsChecker.checkingTransactionType;

/**
 * @author Jana Metz on 22.01.24
 */
@Service
@RequiredArgsConstructor
public  class TransactionServiceImpl implements TransactionService {

    private final TransactionRepository transactionRepository;
    private final DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    private final TransactionMapper transactionMapper;

    protected Transaction createTransaction(String fromId, String toId, Double amount) {
        if (Objects.equals(fromId, toId)) {
            if (amount < 0) {
                return saveTransaction(new Transaction(WITHDRAW, fromId, toId, amount));
            } else {
                return saveTransaction(new Transaction(ATM, fromId, toId, amount));
            }
        } else {
            return saveTransaction(new Transaction(TRANSFER, fromId, toId, amount));
        }
    }

    private Transaction saveTransaction(Transaction transaction) {

        return transactionRepository.save(transaction);
    }

    @Override
    public TransactionDto getTransaction(Long id) {
        return transactionMapper.toDTO(transactionRepository.findById(id)
                .orElseThrow(() -> new ExceptionTransactionNotFound("id = " + id)));
    }

    @Override
    public List<TransactionDto> getAllTransaction(String date, String type, String sort) {
        checkingTransactionType(type);
        checkingDate(date);
        return transactionMapper.transactionsToTransactionDto(getTransactionsWithParameters(date, type, sort));
    }

    private List<Transaction> getTransactionsWithParameters(String date, String type, String sort) {
        boolean dateIsNotNullOrEmpty = date != null && !date.isBlank();
        boolean typeIsNotNullOrEmpty = type != null && !type.isEmpty();
        boolean dateAndTypeAreNotNullOrEmpty = typeIsNotNullOrEmpty && dateIsNotNullOrEmpty;

        if (sort != null && !sort.isBlank()) {
            if (sort.equalsIgnoreCase("dateTime")) {
                // return returnTransactionsOrderedByDateAsc(type, date, dateIsNotNullOrEmpty, typeIsNotNullOrEmpty, dateAndTypeAreNotNullOrEmpty);

            } else if (sort.equalsIgnoreCase("-dateTime")) {
                return returnTransactionsOrderedByDateDesc(type, date, dateIsNotNullOrEmpty, typeIsNotNullOrEmpty, dateAndTypeAreNotNullOrEmpty);

            } else
                return returnTransactionsWithoutOrder(type, date, dateIsNotNullOrEmpty, typeIsNotNullOrEmpty, dateAndTypeAreNotNullOrEmpty);

        } else
            return returnTransactionsWithoutOrder(type, date, dateIsNotNullOrEmpty, typeIsNotNullOrEmpty, dateAndTypeAreNotNullOrEmpty);

        return null;
    }



    private List<Transaction> returnTransactionsWithoutOrder(String type,
                                                             String date,
                                                             boolean dateIsNotNullOrEmpty,
                                                             boolean typeIsNotNullOrEmpty,
                                                             boolean dateAndTypeAreNotNullOrEmpty) {
        if (dateAndTypeAreNotNullOrEmpty) {
            //return all transactions with given TYPE and DATE
        //    return transactionRepository.findByTypeAndCreationDate(TransactionType.valueOf(type), LocalDate.parse(date, format));

        } else if (typeIsNotNullOrEmpty) {
            //return all transactions with given TYPE
           // return transactionRepository.findByType(TransactionType.valueOf(type));

        } else if (dateIsNotNullOrEmpty) {
            //return all transactions with given DATE
            //return transactionRepository.findByCreationDate(LocalDate.parse(date, format));

            //return all transactions
        } else return transactionRepository.findAll();

        return null;
    }

    private List<Transaction> returnTransactionsOrderedByDateDesc(String type,
                                                                  String date,
                                                                  boolean dateIsNotNullOrEmpty,
                                                                  boolean typeIsNotNullOrEmpty,
                                                                  boolean dateAndTypeAreNotNullOrEmpty) {
        if (dateAndTypeAreNotNullOrEmpty) {
            //return all transactions with given TYPE and DATE ordered DESCENDING by DATE
            //  return transactionRepository.findByTypeAndCreationDateOrderByCreationDateDesc(TransactionType.valueOf(type), LocalDate.parse(date, format));

        } else if (typeIsNotNullOrEmpty) {
            //return all transactions with given TYPE ordered DESCENDING by DATE
            //return transactionRepository.findByTypeOrderByCreationDateDesc(TransactionType.valueOf(type));

        } else if (dateIsNotNullOrEmpty) {
            //return all transactions with given DATE ordered DESCENDING by DATE
            //return transactionRepository.findByCreationDateOrderByCreationDateDesc(LocalDate.parse(date, format));

            //return all transactions ordered DESCENDING by DATE
            //  } else return transactionRepository.findAllOrderedDesc();
        }
        return null;
    }

   /* private List<Transaction> returnTransactionsOrderedByDateAsc(String type,
                                                                 String date,
                                                                 boolean dateIsNotNullOrEmpty,
                                                                 boolean typeIsNotNullOrEmpty,
                                                                 boolean dateAndTypeAreNotNullOrEmpty) {
        if (dateAndTypeAreNotNullOrEmpty) {
            //return all transactions with given TYPE and DATE ordered ASCENDING by DATE
            return transactionRepository.findByTypeAndCreationDateOrderByCreationDateAsc(TransactionType.valueOf(type), LocalDate.parse(date, format));

        } else if (typeIsNotNullOrEmpty) {
            //return all transactions with given TYPE ordered ASCENDING by DATE
            return transactionRepository.findByTypeOrderByCreationDateAsc(TransactionType.valueOf(type));

        } else if (dateIsNotNullOrEmpty) {
            //return all transactions with given DATE ordered ASCENDING by DATE
            return transactionRepository.findByCreationDateOrderByCreationDateAsc(LocalDate.parse(date, format));

            //return all transactions ordered ASCENDING by DATE
        } else return transactionRepository.findAllOrderedAsc();
    }*/

    }

