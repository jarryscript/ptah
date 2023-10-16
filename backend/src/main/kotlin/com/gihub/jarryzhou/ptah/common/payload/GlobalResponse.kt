package com.gihub.jarryzhou.ptah.common.payload

class GlobalResponse<T> {
    var code = 0
    var message: String? = null
    var data: T? = null

    companion object {
        fun success(responseData: Any?): GlobalResponse<*> = GlobalResponse<Any?>().apply {
            code = ResultCodeEnum.SUCCESS.code
            message = ResultCodeEnum.SUCCESS.message
            data = responseData
        }
    }
}
