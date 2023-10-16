package com.github.jarryzhou.ptah.common.payload

import com.google.gson.Gson
import org.springframework.core.MethodParameter
import org.springframework.http.MediaType
import org.springframework.http.converter.HttpMessageConverter
import org.springframework.http.server.ServerHttpRequest
import org.springframework.http.server.ServerHttpResponse
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice

@RestControllerAdvice(basePackages = ["com.ptah"])
class GlobalResponseHandler : ResponseBodyAdvice<Any?> {
    private val gson = Gson()
    override fun supports(returnType: MethodParameter, converterType: Class<out HttpMessageConverter<*>>): Boolean =
        true

    override fun beforeBodyWrite(
        data: Any?,
        returnType: MethodParameter,
        selectedContentType: MediaType,
        selectedConverterType: Class<out HttpMessageConverter<*>>,
        request: ServerHttpRequest,
        response: ServerHttpResponse
    ): Any? {
        return if (data is String) {
            gson.toJson(GlobalResponse.success(data))
        } else {
            data as? GlobalResponse<*> ?: GlobalResponse.success(data)
        }
    }
}
