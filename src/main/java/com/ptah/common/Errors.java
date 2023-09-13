package com.ptah.common;

public enum Errors {
    INVALID_CREDENTIALS("10000","Invalid credentials"),
    INSUFFICIENT_FUND("10001","Insufficient fund"),
    USER_NOT_FOUND("10002","User not found"),
    ORGANIZATION_NOT_FOUND("10003","Organization not found"),
    SYSTEM_ACCOUNT_NOT_FOUND("10003","System account not found" );

    private String code;
    private String message;

    Errors(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
