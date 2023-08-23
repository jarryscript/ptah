package com.example.accounting.service;

import com.example.accounting.model.Account;
import com.example.accounting.model.Transaction;
import com.example.accounting.model.TransactionDirection;
import com.example.accounting.repository.AccountRepository;
import com.example.accounting.repository.TransactionRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.Objects;

/**
 * To see your balances, you can run this query:
 * <p>
 * select
 * (account) as a,
 * name,
 * sum(amount * direction * normal) as balance
 * from
 * transactions
 * left join accounts on a = accounts.number
 * group by
 * name
 * order by
 * a,
 * name;
 * To view the ledger, you can run this:
 * <p>
 * select
 * id,
 * date,
 * name,
 * case when direction == 1 then amount end as DR,
 * case when direction == -1 then amount end as CR
 * from
 * transactions
 * left join accounts on account = accounts.number
 * order by
 * id,
 * date,
 * CR,
 * DR;
 */
//@Service
@AllArgsConstructor
public class AccountingService {

    private TransactionRepository transactionRepository;
    private AccountRepository accountRepository;

    public void withdrawal(Account account, BigDecimal amount) {
        validateAccountForWithdrawal(account, amount);
        updateAccountBalanceForWithdrawal(account, amount);
        createWithdrawalTransactions(account, amount);

    }

    private void updateAccountBalanceForWithdrawal(Account account, BigDecimal amount) {
        account.setBalance(account.getBalance().subtract(amount));
//        accountRepository.save(account);
    }

    private void createWithdrawalTransactions(Account account, BigDecimal amount) {
//        transactionRepository.saveAll(Arrays.asList(new Transaction(account, TransactionDirection.DEBIT, amount, LocalTime.now()), new Transaction(getSystemAccount(), TransactionDirection.CREDIT, amount, LocalTime.now())));
    }

    private Account getSystemAccount() {
        return null;
    }

    private void validateAccountForWithdrawal(Account account, BigDecimal amount) {
        Objects.requireNonNull(account);
        Objects.requireNonNull(amount);
    }


    public void deposit(Account account, BigDecimal amount) {
        validateAccountForDeposit(account, amount);
        updateAccountBalanceForDeposit(account, amount);
        createDepositTransactions(account, amount);
    }

    private void createDepositTransactions(Account account, BigDecimal amount) {

    }

    private void updateAccountBalanceForDeposit(Account account, BigDecimal amount) {

    }

    private void validateAccountForDeposit(Account account, BigDecimal amount) {
        Objects.requireNonNull(account);
        Objects.requireNonNull(amount);
    }

    public void transfer(Account sourceAccount, Account targetAccount, BigDecimal amount) {
        validateAccountsForTransfer(sourceAccount, targetAccount, amount);
        updateAccountBalanceForTransfer(sourceAccount, targetAccount, amount);
        createTransferTransactions(sourceAccount, targetAccount, amount);
    }

    private void updateAccountBalanceForTransfer(Account sourceAccount, Account targetAccount, BigDecimal amount) {

    }

    private void createTransferTransactions(Account sourceAccount, Account targetAccount, BigDecimal amount) {

    }

    private void validateAccountsForTransfer(Account sourceAccount, Account targetAccount, BigDecimal amount) {

    }
}
