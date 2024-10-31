package com.management.bookmarkmanagement.user.dto

data class AuthUserResponse(
    val userId: Long,
    val accessToken: String,
)
