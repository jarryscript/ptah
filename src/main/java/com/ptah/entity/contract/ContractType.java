package com.ptah.entity.contract;

public enum ContractType {
    NORMAL_CONTRACT("常规合同"),
    ADDITIONAL_CONTRACT("增补合同")
    ;
    private String displayName;

    ContractType(String displayName) {
        this.displayName = displayName;
    }
}
