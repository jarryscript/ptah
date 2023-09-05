package com.ptah.common;

public enum Errors {
    INVALID_CREDENTIALS("10000","Invalid credentials"),
    INSUFFICIENT_FUND("10001","Insufficient fund"),
    SYSTEM_ACCOUNT_NOT_FOUND("10002","System account not found" );

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
