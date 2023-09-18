package com.ptah.common.exceptions

import com.ptah.common.Errors

class ApplicationException(error: Errors?) : CommonException(error.getMessage()) {
    companion object {
        fun of(error: Errors?): ApplicationException {
            return ApplicationException(error)
        }
    }
}
