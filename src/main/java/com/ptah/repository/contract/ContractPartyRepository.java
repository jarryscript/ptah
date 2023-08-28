package com.ptah.repository.contract;

import com.ptah.entity.contract.Participant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContractPartyRepository extends JpaRepository<Participant,Long> {
}
