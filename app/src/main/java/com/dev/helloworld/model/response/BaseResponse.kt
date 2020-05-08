package com.dev.helloworld.model.response

class BaseResponse<T> (
    val status: Int,
    val success: Boolean,
    val message: String,
    val data: T?
)