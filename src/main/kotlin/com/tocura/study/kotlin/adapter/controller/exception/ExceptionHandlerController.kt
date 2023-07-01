package com.tocura.study.kotlin.adapter.controller.exception

import com.tocura.study.kotlin.adapter.controller.exception.response.ErrorResponse
import com.tocura.study.kotlin.adapter.controller.exception.response.ErrorsResponse
import com.tocura.study.kotlin.exceptions.InternalServerErrorException
import com.tocura.study.kotlin.exceptions.PokeApiNotFoundException
import com.tocura.study.kotlin.exceptions.TrainerNotFoundException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.http.converter.HttpMessageNotReadableException
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler

@ControllerAdvice
class ExceptionHandlerController {

    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun invalidRequestBody(e: MethodArgumentNotValidException): ResponseEntity<ErrorsResponse> {
        var errorsResult = e.getBindingResult()
        val errors = mutableListOf<ErrorResponse>()

        for (error in errorsResult.getFieldErrors()) {
            errors.add(ErrorResponse(
                HttpStatus.BAD_REQUEST.toString(),
                "invalid format for " + error.field,
                error.defaultMessage ?: "invalid format",
            ))
        }

        return ResponseEntity(ErrorsResponse(errors), HttpStatus.BAD_REQUEST)
    }

    @ExceptionHandler(HttpMessageNotReadableException::class)
    fun missingRequestBody(e: HttpMessageNotReadableException): ResponseEntity<ErrorResponse> {
        val errResponse = ErrorResponse(
            HttpStatus.BAD_REQUEST.toString(),
            e.message.toString(),
            e.cause.toString(),
        )

        return ResponseEntity(errResponse, HttpStatus.BAD_REQUEST)
    }

    @ExceptionHandler(IllegalArgumentException::class)
    fun illegalArgumentException(e: IllegalArgumentException): ResponseEntity<ErrorResponse> {
        val errResponse = ErrorResponse(
            HttpStatus.BAD_REQUEST.toString(),
            e.message.toString(),
            e.cause.toString(),
        )

        return ResponseEntity(errResponse, HttpStatus.BAD_REQUEST)
    }

    @ExceptionHandler(TrainerNotFoundException::class)
    fun trainerNotFound(e: TrainerNotFoundException): ResponseEntity<ErrorResponse> {
        val errResponse = ErrorResponse(
            HttpStatus.NOT_FOUND.toString(),
            e.message.toString(),
            e.cause.toString(),
        )
        return ResponseEntity(errResponse, HttpStatus.NOT_FOUND)
    }

    @ExceptionHandler(PokeApiNotFoundException::class)
    fun trainerNotFound(e: PokeApiNotFoundException): ResponseEntity<ErrorResponse> {
        val errResponse = ErrorResponse(
            HttpStatus.NOT_FOUND.toString(),
            e.message.toString(),
            e.cause.toString(),
        )
        return ResponseEntity(errResponse, HttpStatus.NOT_FOUND)
    }

    @ExceptionHandler(InternalServerErrorException::class)
    fun internalServerError(e: InternalServerErrorException): ResponseEntity<ErrorResponse> {
        val errResponse = ErrorResponse(
            HttpStatus.INTERNAL_SERVER_ERROR.toString(),
            e.message.toString(),
            e.cause.toString(),
        )
        return ResponseEntity(errResponse, HttpStatus.INTERNAL_SERVER_ERROR)
    }
}