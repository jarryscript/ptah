package com.ptah.service.accounting;

import com.ptah.common.Errors;
import com.ptah.common.exceptions.ApplicationException;
import com.ptah.common.util.NumberUtils;
import com.ptah.entity.accounting.Account;
import com.ptah.repository.accounting.AccountRepository;
import com.ptah.repository.accounting.TransactionRepository;
import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
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
@Transactional
public class AccountingService {

    private static final Long SYSTEM_ACCOUNT_ID = 1L;
    private static final String SYSTEM_ACCOUNT_NAME = "SYSTEM ACCOUNT";

    private TransactionRepository transactionRepository;
    private AccountRepository accountRepository;

    public void withdrawal(Account account, BigDecimal amount) {
        validateAccountForWithdrawal(account, amount);
        updateAccountBalanceForWithdrawal(account, amount);
        createWithdrawalTransactions(account, amount);

    }

    private void updateAccountBalanceForWithdrawal(Account account, BigDecimal amount) {
        account.setBalance(account.getBalance().subtract(amount));
        accountRepository.save(account);
    }

    private void createWithdrawalTransactions(Account account, BigDecimal amount) {
//        transactionRepository.saveAll(Arrays.asList(new Transaction(account, TransactionDirection.DEBIT, amount, LocalTime.now()), new Transaction(getSystemAccount(), TransactionDirection.CREDIT, amount, LocalTime.now())));
    }

    private void validateAccountForWithdrawal(Account account, BigDecimal amount) {
        Objects.requireNonNull(account);
        Objects.requireNonNull(amount);
        if (!NumberUtils.isGreaterThanOrEqualsTo(account.getBalance(), amount)) {
            throw new ApplicationException(Errors.INSUFFICIENT_FUND);
        }
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

    private Account getSystemAccount() {
        return accountRepository.findById(SYSTEM_ACCOUNT_ID).orElseGet(this::createSystemAccount);
    }

    private Account createSystemAccount() {
        Account systemAccount = new Account();
        systemAccount.setId(SYSTEM_ACCOUNT_ID);
        systemAccount.setName(SYSTEM_ACCOUNT_NAME);
        return accountRepository.save(systemAccount);
    }
}
