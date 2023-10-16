package com.gihub.jarryzhou.ptah.common.exceptions

import com.ptah.common.payload.GlobalResponse
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler

@ControllerAdvice(basePackages = ["com.ptah"])
class GlobalExceptionHandler {

    @ExceptionHandler(Exception::class)
    fun handleException(e: Exception): ResponseEntity<GlobalResponse<*>> =
        ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
            GlobalResponse<Any?>().apply {
                code = HttpStatus.INTERNAL_SERVER_ERROR.value()
                message = e.message ?: e.stackTraceToString()
            }
        )

    @ExceptionHandler(ApplicationException::class)
    fun handleApplicationRuntimeException(e: ApplicationException): ResponseEntity<GlobalResponse<*>> =
        ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
            GlobalResponse<Any?>().apply {
                message = e.message
            }
        )

    @ExceptionHandler(SecurityException::class)
    fun handleAuthRuntimeException(e: SecurityException): ResponseEntity<GlobalResponse<*>> {
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(
            GlobalResponse<Any?>().apply {
                message = e.message
            }
        )
    }
}
