package com.github.jarryzhou.ptah.repository.contract

import com.github.jarryzhou.ptah.entity.contract.Participant
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ContractPartyRepository : JpaRepository<Participant?, Long?>
