package com.management.bookmarkmanagement.user.dto

data class SignInRequest(
    val email: String,
    val password: String
) {
    fun toDto() = SignInDto(
        email = email,
        password = password
    )
}
