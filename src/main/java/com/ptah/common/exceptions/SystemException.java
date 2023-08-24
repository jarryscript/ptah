package com.ptah.common.exceptions;

import com.ptah.common.Errors;

public class SystemException extends CommonException {

    public SystemException(Errors error) {
        super(error.getCode() + " : " + error.getMessage());
    }

}
