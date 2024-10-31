package com.management.bookmarkmanagement.user.application

import com.management.bookmarkmanagement.user.dao.UserAuthRepository
import com.management.bookmarkmanagement.user.dto.NewAuthUser
import com.management.bookmarkmanagement.user.dto.SignUpDto
import com.management.bookmarkmanagement.user.exception.DuplicatedUserException
import org.springframework.stereotype.Service

@Service
class UserAuthService (
    private val userAuthRepository: UserAuthRepository
) {
    fun userSignUp(signUpDto: SignUpDto): NewAuthUser {
        userAuthRepository.findByEmail(email = signUpDto.email)?.let {
            throw DuplicatedUserException()
        }

        val userId = userAuthRepository.saveNewUser(signUpDto = signUpDto)

        // accessToken 발급 (JWT)
        val jwtToken = "123"

        return NewAuthUser(
            userId = userId, accessToken = jwtToken
        )
    }
}