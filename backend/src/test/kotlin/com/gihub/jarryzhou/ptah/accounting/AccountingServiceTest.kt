package com.gihub.jarryzhou.ptah.accounting

import com.ptah.common.Errors
import com.ptah.common.exceptions.ApplicationException
import com.ptah.entity.accounting.Account
import com.ptah.entity.accounting.Transaction
import com.ptah.entity.contract.Participant
import com.ptah.repository.accounting.AccountRepository
import com.ptah.repository.accounting.TransactionRepository
import com.ptah.service.accounting.AccountingService
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import io.mockk.mockk
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.api.extension.ExtendWith
import java.math.BigDecimal
import java.util.*

@ExtendWith(MockKExtension::class)
class AccountingServiceTest {
    @InjectMockKs
    lateinit var accountingService: AccountingService

    @MockK
    lateinit var transactionRepository: TransactionRepository

    @MockK
    lateinit var accountRepository: AccountRepository

    @BeforeEach
    fun init() {
        every { accountRepository.save(any<Account>()) } answers {
            it.invocation.args[0] as Account
        }
        every { accountRepository.findById(any()) } returns Optional.of(mockk<Account>()).map { it }

        every { transactionRepository.saveAll(any<Iterable<Transaction>>()) } answers {
            (it.invocation.args[0] as Iterable<Transaction>).toList()
        }
    }

    @Test
    fun withdrawal_ShouldWithdrawalSuccessfully_WhenWithdrawalAmountIsLessThanBalance() {
        val account = Account(
            TEST_ACCOUNT_NAME,
            BigDecimal.TEN,
            mockk<Participant>()
        )
        accountingService.withdrawal(account, BigDecimal.ONE)
        assertEquals(BigDecimal.valueOf(9), account.balance)
    }

    @Test
    fun withdrawal_ShouldGetInsufficientFundException_WhenWithdrawalAmountIsGreaterThanBalance() {
        val account = Account(
            TEST_ACCOUNT_NAME,
            BigDecimal.TEN,
            mockk<Participant>()
        )
        assertThrows<ApplicationException> {
            Errors.INSUFFICIENT_FUND.message
            accountingService.withdrawal(
                account,
                BigDecimal.TEN.multiply(BigDecimal.TEN)
            )
        }
    }

    @Test
    fun withdrawal_ShouldWithdrawalSuccessfully_WhenWithdrawalAmountEqualsToBalance() {
        val account = Account(
            TEST_ACCOUNT_NAME,
            BigDecimal.TEN,
            mockk<Participant>()
        )
        accountingService.withdrawal(account, BigDecimal.TEN)
        assertEquals(BigDecimal.ZERO, account.balance)
    }

    companion object {
        private const val TEST_ACCOUNT_NAME = "test"
    }
}
