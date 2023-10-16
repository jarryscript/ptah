package com.github.jarryzhou.ptah.service.contract

import com.github.jarryzhou.ptah.common.Errors
import com.github.jarryzhou.ptah.common.exceptions.ApplicationException
import com.github.jarryzhou.ptah.entity.contract.Contract
import com.github.jarryzhou.ptah.entity.contract.ContractStatus
import com.github.jarryzhou.ptah.repository.contract.ContractRepository
import com.github.jarryzhou.ptah.service.accounting.AccountingService
import org.springframework.stereotype.Service

@Service
class ContractService(
    private var contractRepository: ContractRepository,
    private var accountingService: AccountingService
) {
    fun createContract() {}

    fun acceptContract(contractId: Long) {
        val contract = contractRepository.findById(contractId)
            .orElseThrow { ApplicationException.of(Errors.ORGANIZATION_NOT_FOUND) }
        validateContractForAcceptance(contract!!)
        payContract(contract)
        contractRepository.save(contract.apply { status = ContractStatus.ACCEPTED })
    }

    private fun payContract(contract: Contract) {
        val partyAAccount = accountingService.getAccountByOwnerId(contract.partyA.id!!)
        val partyBAccount = accountingService.getAccountByOwnerId(contract.partyB.id!!)
        accountingService.transfer(partyAAccount, partyBAccount, contract.amount)
    }

    private fun validateContractForAcceptance(contract: Contract) {
        TODO("Not yet implemented")
    }
}
