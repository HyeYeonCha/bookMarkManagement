package com.management.bookmarkmanagement.user.dto

import java.time.LocalDateTime

data class UserDto(
    val id: Long,
    val userName: String,
    val email: String,
    val createdDateTime: LocalDateTime,
)
