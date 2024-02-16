package com.example.bankingapp.entity.enums;

/**
 * @author Jana Metz on 21.01.24
 */
public enum TransactionType {
    /*Bank account transaction types:
     * ATM: Deposit or withdraw funds using an ATM.
     * Online: Withdraw funds through a web-based store or online banking service.
     * Withdrawal: Deduct funds from an account by any method
     * Transfer: This likely represents a general money transfer operation. It could involve transferring funds between different accounts, either within the same bank or between differ*/
    ATM,
    WITHDRAW,
    TRANSFER,
    ONLINE
}
