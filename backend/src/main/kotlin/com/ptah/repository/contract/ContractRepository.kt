package com.ptah.repository.contract

import com.ptah.entity.contract.Contract
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ContractRepository : JpaRepository<Contract?, Long?>
