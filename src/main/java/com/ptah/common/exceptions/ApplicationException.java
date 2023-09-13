package com.ptah.common.exceptions;

import camundajar.impl.scala.App;
import com.ptah.common.Errors;

public class ApplicationException extends CommonException{

    public ApplicationException(Errors error) {
        super(error.getMessage());
    }

    public static ApplicationException of(Errors error){
        return new ApplicationException(error);
    }

}
