package com.ptah.accounting;

import com.ptah.common.Errors;
import com.ptah.common.exceptions.ApplicationException;
import com.ptah.entity.accounting.Account;
import com.ptah.entity.accounting.Transaction;
import com.ptah.entity.contract.Participant;
import com.ptah.repository.accounting.AccountRepository;
import com.ptah.repository.accounting.TransactionRepository;
import com.ptah.service.accounting.AccountingService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.math.BigDecimal;

public class AccountingServiceTest {

    private AccountingService accountingService;
    private TransactionRepository transactionRepository;
    private AccountRepository accountRepository;

    @BeforeEach
    void init() {
        transactionRepository = Mockito.mock(TransactionRepository.class);
        accountRepository = Mockito.mock(AccountRepository.class);
        accountingService = new AccountingService(transactionRepository, accountRepository);
        Mockito.when(accountRepository.save(Mockito.any(Account.class))).thenAnswer(invocation -> invocation.getArgument(0));
        Mockito.when(transactionRepository.save(Mockito.any(Transaction.class))).thenAnswer(invocation -> invocation.getArgument(0));
    }


    @Test
    void withdrawal_ShouldWithdrawalSuccessfully_WhenAmountIsValid() {
        Account account = new Account(Mockito.anyString(), BigDecimal.TEN, Mockito.mock(Participant.class));

        accountingService.withdrawal(account, BigDecimal.ONE);

        Assertions.assertEquals(BigDecimal.valueOf(9), account.getBalance());
    }

    @Test
    void withdrawal_ShouldGetInsufficientFundException_WhenWithdrawalAmountIsGreaterThanBalance() {
        Account account = new Account(Mockito.anyString(), BigDecimal.TEN, Mockito.mock(Participant.class));

        Assertions.assertThrows(ApplicationException.class, () -> accountingService.withdrawal(account, BigDecimal.TEN.multiply(BigDecimal.TEN)), Errors.INSUFFICIENT_FUND.getMessage());
    }
}
