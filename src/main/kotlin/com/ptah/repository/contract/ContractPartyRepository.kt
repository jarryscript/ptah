package com.ptah.repository.contract

import com.ptah.entity.contract.Participant
import org.springframework.data.jpa.repository.JpaRepository

interface ContractPartyRepository : JpaRepository<Participant?, Long?>
