package com.ptah.service.accounting

import com.ptah.common.Errors
import com.ptah.common.exceptions.ApplicationException
import com.ptah.common.util.NumberUtils
import com.ptah.entity.accounting.Account
import com.ptah.entity.accounting.Transaction
import com.ptah.entity.accounting.TransactionDirection
import com.ptah.repository.accounting.AccountRepository
import com.ptah.repository.accounting.TransactionRepository
import org.springframework.transaction.annotation.Transactional
import java.math.BigDecimal
import java.util.*

/**
 * To see your balances, you can run this query:
 *
 *
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
 *
 *
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
@Transactional
class AccountingService {
    private val transactionRepository: TransactionRepository? = null
    private val accountRepository: AccountRepository? = null
    fun withdrawal(account: Account, amount: BigDecimal) {
        validateAccountForWithdrawal(account, amount)
        updateAccountBalanceForWithdrawal(account, amount)
        createWithdrawalTransactions(account, amount)
    }

    private fun updateAccountBalanceForWithdrawal(account: Account, amount: BigDecimal) {
        account.balance?.subtract(amount)
        accountRepository!!.save(account)
    }

    private fun createWithdrawalTransactions(account: Account, amount: BigDecimal) {
        val debit = Transaction(account, TransactionDirection.DEBIT, amount)
        val credit = Transaction(systemAccount, TransactionDirection.CREDIT, amount)
        transactionRepository!!.saveAll(Arrays.asList(debit, credit))
    }

    private val systemAccount: Account
        get() = accountRepository!!.findById(SYSTEM_ACCOUNT_ID).orElseGet { createSystemAccount() }!!

    fun createSystemAccount(): Account {
        val systemAccount = Account()
        systemAccount.id = SYSTEM_ACCOUNT_ID
        systemAccount.name = SYSTEM_ACCOUNT_NAME
        return accountRepository!!.save(systemAccount)
    }

    fun validateAccountForWithdrawal(account: Account, amount: BigDecimal) {
        Objects.requireNonNull(account)
        Objects.requireNonNull(amount)
        if (!NumberUtils.isGreaterThanOrEqualsTo(account.balance, amount)) {
            throw ApplicationException(Errors.INSUFFICIENT_FUND)
        }
    }

    fun deposit(account: Account, amount: BigDecimal) {
        validateAccountForDeposit(account, amount)
        updateAccountBalanceForDeposit(account, amount)
        createDepositTransactions(account, amount)
    }

    fun createDepositTransactions(account: Account, amount: BigDecimal) {}
    fun updateAccountBalanceForDeposit(account: Account, amount: BigDecimal) {}
    fun validateAccountForDeposit(account: Account, amount: BigDecimal) {
        Objects.requireNonNull(account)
        Objects.requireNonNull(amount)
    }

    fun transfer(sourceAccount: Account, targetAccount: Account, amount: BigDecimal) {
        validateAccountsForTransfer(sourceAccount, targetAccount, amount)
        updateAccountBalanceForTransfer(sourceAccount, targetAccount, amount)
        createTransferTransactions(sourceAccount, targetAccount, amount)
    }

    fun updateAccountBalanceForTransfer(sourceAccount: Account, targetAccount: Account, amount: BigDecimal) {}
    fun createTransferTransactions(sourceAccount: Account, targetAccount: Account, amount: BigDecimal) {}
    fun validateAccountsForTransfer(sourceAccount: Account, targetAccount: Account, amount: BigDecimal) {}

    companion object {
        private const val SYSTEM_ACCOUNT_ID = 1L
        private const val SYSTEM_ACCOUNT_NAME = "SYSTEM ACCOUNT"
    }
}
