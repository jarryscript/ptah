package com.ptah.service.contract

import com.ptah.common.Errors
import com.ptah.common.exceptions.ApplicationException
import com.ptah.entity.contract.Contract
import com.ptah.entity.contract.ContractStatus
import com.ptah.repository.contract.ContractRepository
import com.ptah.service.accounting.AccountingService
import org.springframework.stereotype.Service

@Service
class ContractService(
    private var contractRepository: ContractRepository, private var accountingService: AccountingService
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
