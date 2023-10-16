package com.gihub.jarryzhou.ptah.common.exceptions

import com.ptah.common.Errors

class ApplicationException(error: Errors?) : CommonException(error?.message) {
    companion object {
        fun of(error: Errors?): ApplicationException = ApplicationException(error)
    }
}
