package com.management.bookmarkmanagement.user.application

import com.management.bookmarkmanagement.user.dao.UserAuthRepository
import com.management.bookmarkmanagement.user.dto.UserDto
import com.management.bookmarkmanagement.user.exception.NoUserException
import org.springframework.stereotype.Service

@Service
class UserService(
    private val userAuthRepository: UserAuthRepository,
) {
    fun getUserByEmail(email: String): UserDto {
        return userAuthRepository.findByEmail(email)?.run { toDto() } ?: throw NoUserException()
    }
}