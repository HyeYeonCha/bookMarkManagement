package com.management.bookmarkmanagement.user.dto

data class UserResponse(
    val id: Long,
    val email: String,
    val name: String,
    val createdDateTime: String,
)