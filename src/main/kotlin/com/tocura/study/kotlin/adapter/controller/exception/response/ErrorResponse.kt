package com.tocura.study.kotlin.adapter.controller.exception.response

class ErrorResponse(
    var code: String,
    var message: String,
    var detail: String,
)

class ErrorsResponse(
    var errors: List<ErrorResponse>
)