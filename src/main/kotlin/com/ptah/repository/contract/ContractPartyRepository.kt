package com.ptah.repository.contract

import com.ptah.entity.contract.Participant
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ContractPartyRepository : JpaRepository<Participant?, Long?>
