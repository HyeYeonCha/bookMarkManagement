package com.management.bookmarkmanagement.user.application

import com.management.bookmarkmanagement.jwt.JwtUtil
import com.management.bookmarkmanagement.user.dao.UserAuthRepository
import com.management.bookmarkmanagement.user.dto.AuthUser
import com.management.bookmarkmanagement.user.dto.SignInDto
import com.management.bookmarkmanagement.user.dto.SignUpDto
import com.management.bookmarkmanagement.user.exception.DuplicatedUserException
import com.management.bookmarkmanagement.user.exception.NoMatchPasswordException
import com.management.bookmarkmanagement.user.exception.NoUserException
import org.springframework.stereotype.Service

@Service
class UserAuthService (
    private val userAuthRepository: UserAuthRepository,
    private val jwtUtil: JwtUtil,
) {
    fun userSignUp(signUpDto: SignUpDto): AuthUser {
        userAuthRepository.findByEmail(email = signUpDto.email)?.let {
            throw DuplicatedUserException()
        }

        val userId = userAuthRepository.saveNewUser(signUpDto = signUpDto)

        val jwtToken = jwtUtil.generateToken(email = signUpDto.email)

        return AuthUser(
            userId = userId, accessToken = jwtToken
        )
    }

    fun userSignIn(signInDto: SignInDto): AuthUser {
        return userAuthRepository.findByEmail(email = signInDto.email)?.run {
            validatePassword(signInDto.password) || throw NoMatchPasswordException()

            val jwtToken = jwtUtil.generateToken(email = signInDto.email)

            return AuthUser(
                userId = this.id, accessToken = jwtToken
            )
        } ?: throw NoUserException()
    }
}