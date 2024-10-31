package com.management.bookmarkmanagement.user.application

import com.management.bookmarkmanagement.jwt.JwtUtil
import com.management.bookmarkmanagement.user.dao.UserAuthRepository
import com.management.bookmarkmanagement.user.domain.UserEntity
import com.management.bookmarkmanagement.user.dto.SignInDto
import com.management.bookmarkmanagement.user.dto.SignUpDto
import com.management.bookmarkmanagement.user.exception.DuplicatedUserException
import com.management.bookmarkmanagement.user.exception.NoMatchPasswordException
import com.management.bookmarkmanagement.user.exception.NoUserException
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify

class UserAuthServiceTest : FunSpec({

    val userAuthRepository: UserAuthRepository = mockk()
    val jwtUtil: JwtUtil = mockk()
    val sut = UserAuthService(
        userAuthRepository = userAuthRepository,
        jwtUtil = jwtUtil,
    )

    context("signUp") {
        test("email 로 user 를 찾았을 때 동일 user 가 있다면 DuplicatedUserException 이 발생한다.") {
            // GIVEN
            val email = "duplicatedUserEmail"
            val password = "1234"

            val signUpDto = SignUpDto(email = email, password = password)

            every {
                userAuthRepository.findByEmail(email)
            }.returns(UserEntity(newEmail = email, newPassword = password))

            // WHEN && THEN
            val exception = shouldThrow<DuplicatedUserException> {
                sut.userSignUp(signUpDto = signUpDto)
            }

            exception.message shouldBe "중복 유저로 가입불가"
        }

        test("email 로 user 를 찾았을 때 동일 user 가 없다면 토큰과 userId 가 반환된다.") {
            // GIVEN
            val email = "userEmail"
            val password = "1234"
            val testAccessToken = "123"

            val signUpDto = SignUpDto(email = email, password = password)

            every { userAuthRepository.findByEmail(email) }.returns(null)
            every { jwtUtil.generateToken(email) }.returns(testAccessToken)
            every { userAuthRepository.saveNewUser(any()) }.returns(1L)

            // WHEN
            val result = sut.userSignUp(signUpDto = signUpDto)

            verify { userAuthRepository.saveNewUser(signUpDto = signUpDto) }
            result.userId shouldBe result.userId
            result.accessToken shouldBe testAccessToken
        }
    }

    context("signIn") {
        test("email 로 user 를 찾았을 때 user 가 없다면 NoUserException 이 발생한다.") {
            // GIVEN
            val email = "userEmail"
            val password = "1234"

            val signInDto = SignInDto(email = email, password = password)

            every { userAuthRepository.findByEmail(email) }.returns(null)

            // WHEN && THEN
            val exception = shouldThrow<NoUserException> {
                sut.userSignIn(signInDto = signInDto)
            }

            exception.message shouldBe "존재하지 않는 유저입니다."
        }

        test("email 로 user 를 찾았을 때 password 가 일치하지 않다면 NoMatchPasswordException 이 발생한다.") {
            // GIVEN
            val email = "userEmail"
            val password = "1234"

            val signInDto = SignInDto(email = email, password = password)

            every {
                userAuthRepository.findByEmail(email)
            }.returns(UserEntity(newEmail = email, newPassword = "123456"))

            // WHEN && THEN
            val exception = shouldThrow<NoMatchPasswordException> {
                sut.userSignIn(signInDto = signInDto)
            }

            exception.message shouldBe "비밀번호가 일치하지 않습니다."
        }

        test("email 로 user 를 찾았을 때 password 가 일치한다면 토큰과 userId 가 반환된다.") {
            // GIVEN
            val email = "userName"
            val password = "1234"
            val testAccessToken = "123"

            val signInDto = SignInDto(email = email, password = password)

            every { userAuthRepository.findByEmail(email) }
                .returns(UserEntity(newEmail = email, newPassword = password))
            every { jwtUtil.generateToken(email) }.returns(testAccessToken)

            // WHEN
            val result = sut.userSignIn(signInDto = signInDto)

            result.userId shouldBe result.userId
            result.accessToken shouldBe testAccessToken
        }
    }

})
