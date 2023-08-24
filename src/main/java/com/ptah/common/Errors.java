package com.ptah.common;

public enum Errors {
    INVALID_CREDENTIALS("10000","Invalid credentials")
    ;

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
