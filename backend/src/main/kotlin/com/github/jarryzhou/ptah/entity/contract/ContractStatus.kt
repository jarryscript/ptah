package com.github.jarryzhou.ptah.entity.contract

enum class ContractStatus(private val displayName: String) {
    PENDING("常规合同"),
    PROCESSING("增补合同"),
    PENDING_ACCEPTANCE("常规合同"),
    ACCEPTED("常规合同")
}
