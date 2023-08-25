package com.ptah.repository.accounting;

import com.ptah.entity.accounting.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository
        extends JpaRepository<Account,Long>
{
}
