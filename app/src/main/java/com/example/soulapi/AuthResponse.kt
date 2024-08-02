package com.example.soulapi

data class AuthResponse(
    val message: String,
    val admin: Boolean? = null,
    val error: String? = null
)