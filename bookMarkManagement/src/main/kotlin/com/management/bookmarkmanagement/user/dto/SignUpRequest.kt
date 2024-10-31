package com.management.bookmarkmanagement.user.dto

data class SignUpRequest(
    val email: String,
    val password: String,
) {
    fun toDto() = SignUpDto(
        email = email,
        password = password,
    )
}
