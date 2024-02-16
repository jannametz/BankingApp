package com.example.bankingapp.service.util;

import com.example.bankingapp.entity.Account;
import com.example.bankingapp.entity.enums.TransactionType;
import com.example.bankingapp.errorandexception.ExceptionDateAnomaly;
import com.example.bankingapp.errorandexception.ExceptionBankAccountNotFound;
import com.example.bankingapp.errorandexception.ExceptionNotOnBudget;
import com.example.bankingapp.errorandexception.ExceptionStatusInvalidTypeTransaction;
import lombok.experimental.UtilityClass;
import org.apache.coyote.BadRequestException;

import java.time.format.DateTimeParseException;
import java.util.Objects;

import static com.example.bankingapp.errorandexception.sendMessage.messageError.INVALID_AMOUNT;
import static com.example.bankingapp.errorandexception.sendMessage.messageError.NEGATIVE_TRANSFER_AMOUNT;
import static java.lang.Math.abs;
import static java.time.LocalDate.parse;

/**
 * @author Jana Metz on 24.01.24
 */
@UtilityClass
public class PullRequestsChecker {
    public static void checkingAccount(Account account, String id) {
        if (account == null) throw new ExceptionBankAccountNotFound(id);
    }

    public static void checkingDate(String date) {
        if (date == null) return;

        try {
            parse(date);
        } catch (DateTimeParseException e) {
            throw new ExceptionDateAnomaly(date);
        }
    }

    public static void checkingTransactionType(String type) {
        if (type == null) return;

        for (TransactionType tt : TransactionType.values()) {
            if (type.equals(tt.name())) return;
        }

        throw new ExceptionStatusInvalidTypeTransaction(type);
    }

    public static void checkingTansactionConectPosobbility(String fromId, String toId, Double amount, Account fromAccount, Account toAccount) {
        if (amount == 0) try {
            throw new BadRequestException(INVALID_AMOUNT.getMessage());
        } catch (BadRequestException e) {
            throw new RuntimeException(e);
        }
        checkingAccount(fromAccount, fromId);
        checkingAccount(toAccount, toId);

        if (Objects.equals(fromAccount, toAccount) && amount < 0 && abs(amount) > fromAccount.getMoneyAmount().doubleValue())
            throw new ExceptionNotOnBudget(fromId);

        if (!Objects.equals(fromAccount, toAccount)) {
            if (amount < 0) try {
                throw new BadRequestException(NEGATIVE_TRANSFER_AMOUNT.getMessage());
            } catch (BadRequestException e) {
                throw new RuntimeException(e);
            }

            if (abs(amount) > fromAccount.getMoneyAmount().doubleValue())
                throw new ExceptionNotOnBudget(fromId);
        }
    }
}
