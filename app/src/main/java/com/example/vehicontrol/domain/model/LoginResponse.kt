package com.example.vehicontrol.domain.model

data class LoginResponse(
    val status: String,
    val message: String,
    val user_id: Int? = null
)