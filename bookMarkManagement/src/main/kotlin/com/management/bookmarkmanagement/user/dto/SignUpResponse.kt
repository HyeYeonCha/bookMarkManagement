package com.management.bookmarkmanagement.user.dto

data class SignUpResponse(
    val userId: Long,
    val accessToken: String,
)
