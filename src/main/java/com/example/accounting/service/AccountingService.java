package com.example.accounting.service;

import com.example.accounting.model.Account;
import com.example.accounting.model.Transaction;
import com.example.accounting.model.TransactionDirection;

import java.math.BigDecimal;
import java.time.LocalTime;

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
public class AccountingService {

    public void withdrawal(Account account, BigDecimal amount) {
        validateAccountForWithdrawal();
        account.setBalance(account.getBalance().subtract(amount));
        new Transaction(account, TransactionDirection.DEBIT, amount, LocalTime.now());
        new Transaction(getLedger(), TransactionDirection.CREDIT, amount, LocalTime.now());
    }

    private void validateAccountForWithdrawal() {

    }

    private Account getLedger() {
        return null;
    }

    public void deposit(Account account, BigDecimal amount) {

    }

    public void transfer(Account sourceAccount, Account targetAccount, BigDecimal amount) {

    }
}
