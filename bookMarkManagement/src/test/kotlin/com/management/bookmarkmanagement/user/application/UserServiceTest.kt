package com.management.bookmarkmanagement.user.application

import com.management.bookmarkmanagement.user.dao.UserAuthRepository
import com.management.bookmarkmanagement.user.domain.UserEntity
import com.management.bookmarkmanagement.user.exception.NoUserException
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import io.mockk.every
import io.mockk.mockk

class UserServiceTest : FunSpec({

    val userAuthRepository: UserAuthRepository = mockk()
    val sut = UserService(userAuthRepository = userAuthRepository)

    context("signIn") {
        test("email 로 user 를 찾았을 때 user 가 없다면 NoUserException 이 발생한다.") {
            // GIVEN
            val email = "userEmail"

            every { userAuthRepository.findByEmail(email) }.returns(null)

            // WHEN && THEN
            val exception = shouldThrow<NoUserException> {
                sut.getUserByEmail(email = email)
            }

            exception.message shouldBe "존재하지 않는 유저입니다."
        }

        test("email 로 user 를 찾았을 때 user 가 있다면 user 정보를 반환한다.") {
            // GIVEN
            val email = "userEmail"
            val userName = "testUserName"

            every { userAuthRepository.findByEmail(email) }.returns(
                UserEntity(
                    newEmail = email,
                    newName = userName,
                    newPassword = ""
                )
            )

            // WHEN
            val result = sut.getUserByEmail(email = email)

            // THEN
            result.email shouldBe email
            result.userName shouldBe userName
        }
    }

})
