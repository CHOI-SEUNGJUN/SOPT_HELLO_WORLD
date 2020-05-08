package com.dev.helloworld.model.request

data class SignUpRequest(
    val id: String,
    val password: String,
    val name: String,
    val email: String,
    val phone: String
)