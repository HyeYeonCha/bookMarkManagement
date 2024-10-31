package com.management.bookmarkmanagement.user.application

import com.management.bookmarkmanagement.user.dao.UserAuthRepository
import com.management.bookmarkmanagement.user.dto.SignUpDto
import com.management.bookmarkmanagement.user.exception.DuplicatedUserException
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import io.mockk.mockk

class UserAuthServiceTest : FunSpec({

    val userAuthRepository: UserAuthRepository = mockk()
    val sut = UserAuthService(userAuthRepository = userAuthRepository)


    context("signUp") {
        test("email 로 user 를 찾았을 때 동일 user 가 있다면 DuplicatedUserException 이 발생한다.") {
            // GIVEN
            val email = "duplicatedUserName"
            val password = "1234"

            val signUpDto = SignUpDto(email = email, password = password)

            // todo 현재 통과 안 됨
            // WHEN && THEN
            val exception = shouldThrow<DuplicatedUserException> {
                sut.userSignUp(signUpDto = signUpDto)
            }

            exception.message shouldBe "중복 유저로 가입 불가"
        }

        test("email 로 user 를 찾았을 때 동일 user 가 없다면 토큰과 userId 가 반환된다.") {
            // GIVEN
            val email = "userName"
            val password = "1234"
            val testAccessToken = "123"

            val signUpDto = SignUpDto(email = email, password = password)

            // WHEN
            val result = sut.userSignUp(signUpDto = signUpDto)

            result.userId shouldBe 123L
            result.accessToken shouldBe testAccessToken
        }
    }

})
