package com.ptah.repository.accounting;

import com.ptah.entity.accounting.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository
        extends JpaRepository<Transaction,Long>
{
}
