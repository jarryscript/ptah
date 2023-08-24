package com.ptah.common.exceptions;

import com.ptah.common.Errors;

public class ApplicationException extends CommonException{

    public ApplicationException(Errors error) {
        super(error.getMessage());
    }

}
