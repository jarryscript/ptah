package com.github.jarryzhou.ptah.common.exceptions

import com.github.jarryzhou.ptah.common.Errors

class SystemException(error: Errors) : CommonException(error.code + " : " + error.message)
