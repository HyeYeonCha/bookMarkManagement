package com.management.bookmarkmanagement.user.dto

data class SignUpRequest(
    val email: String,
    val name: String,
    val password: String,
) {
    fun toDto() = SignUpDto(
        email = email,
        name = name,
        password = password,
    )
}
