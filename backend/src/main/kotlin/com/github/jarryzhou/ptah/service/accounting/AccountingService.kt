package com.github.jarryzhou.ptah.service.accounting

import com.github.jarryzhou.ptah.common.Errors
import com.github.jarryzhou.ptah.common.exceptions.ApplicationException
import com.github.jarryzhou.ptah.common.util.NumberUtils
import com.github.jarryzhou.ptah.entity.accounting.Account
import com.github.jarryzhou.ptah.entity.accounting.Transaction
import com.github.jarryzhou.ptah.entity.accounting.TransactionDirection
import com.github.jarryzhou.ptah.repository.accounting.AccountRepository
import com.github.jarryzhou.ptah.repository.accounting.TransactionRepository
import org.springframework.stereotype.Service
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
@Transactional
@Service
class AccountingService(
    private var transactionRepository: TransactionRepository,
    private var accountRepository: AccountRepository
) {
    fun withdrawal(account: Account, amount: BigDecimal) {
        validateAccountForWithdrawal(account, amount)
        updateAccountBalanceForWithdrawal(account, amount)
        createWithdrawalTransactions(account, amount)
    }

    private fun updateAccountBalanceForWithdrawal(account: Account, amount: BigDecimal) {
        account.balance = account.balance?.subtract(amount)
        accountRepository.save(account)
    }

    private fun createWithdrawalTransactions(account: Account, amount: BigDecimal) {
        val debit = Transaction(account, TransactionDirection.DEBIT, amount)
        val credit = Transaction(getSystemAccount(), TransactionDirection.CREDIT, amount)
        transactionRepository.saveAll(listOf(debit, credit))
    }

    fun getSystemAccount() = accountRepository.findById(SYSTEM_ACCOUNT_ID).orElseGet { createSystemAccount() }!!
    fun getAccountByOwnerId(ownerId: Long) = accountRepository.findByOwnerId(ownerId)

    fun createSystemAccount(): Account {
        val systemAccount = Account(
            "System",
            BigDecimal.TEN,
            null
        )
        systemAccount.id = SYSTEM_ACCOUNT_ID
        systemAccount.name = SYSTEM_ACCOUNT_NAME
        return accountRepository.save(systemAccount)
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
