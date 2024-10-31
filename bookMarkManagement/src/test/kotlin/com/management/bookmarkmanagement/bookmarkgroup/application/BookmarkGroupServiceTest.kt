package com.management.bookmarkmanagement.bookmarkgroup.application

import com.management.bookmarkmanagement.bookmarkgroup.dao.BookmarkGroupRepository
import com.management.bookmarkmanagement.bookmarkgroup.exception.DuplicatedBookmarkGroupException
import com.management.bookmarkmanagement.user.application.UserService
import com.management.bookmarkmanagement.user.dao.UserAuthRepository
import com.management.bookmarkmanagement.user.domain.UserEntity
import com.management.bookmarkmanagement.user.exception.NoUserException
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import io.mockk.every
import io.mockk.mockk

class BookmarkGroupServiceTest : FunSpec({

    val userAuthRepository: UserAuthRepository = mockk()
    val userService = UserService(userAuthRepository = userAuthRepository)
    val bookmarkGroupRepository: BookmarkGroupRepository = mockk()

    val sut = BookmarkGroupService(
        bookmarkGroupRepository = bookmarkGroupRepository,
        userService = userService,
    )

    context("createBookmarkGroup") {
        test("email 로 user 를 찾았을 때 user 가 없다면 NoUserException 이 발생한다.") {
            // GIVEN
            val email = "userEmail"
            val groupName = "groupName"

            every { userAuthRepository.findByEmail(email) }.returns(null)

            // WHEN && THEN
            val exception = shouldThrow<NoUserException> {
                sut.createBookmarkGroup(email = email, groupName = groupName)
            }

            exception.message shouldBe "존재하지 않는 유저입니다."
        }

        test("userId 와 groupName 으로 bookmarkGroup 를 찾았을 때 bookmarkGroup 있다면 DuplicatedBookmarkGroupException 이 발생한다.") {
            // GIVEN
            val email = "userEmail"
            val groupName = "groupName"
            val userEntity = UserEntity(newEmail = email, newName = "", newPassword = "")

            every { userAuthRepository.findByEmail(email) }.returns(userEntity)
            every { bookmarkGroupRepository.existsBookmarkGroup(userId = userEntity.id, groupName = groupName) }.returns(true)

            // WHEN && THEN
            val exception = shouldThrow<DuplicatedBookmarkGroupException> {
                sut.createBookmarkGroup(email = email, groupName = groupName)
            }

            exception.message shouldBe "이미 존재하는 찜서랍입니다."
        }

        test("중복되지 않은 찜서랍이라면 저장 후 찜서랍 ID 를 반환한다.") {
            // GIVEN
            val email = "userEmail"
            val groupName = "groupName"
            val userEntity = UserEntity(newEmail = email, newName = "", newPassword = "")
            val bookmarkGroupId = 1L

            every { userAuthRepository.findByEmail(email) }.returns(UserEntity(newEmail = email, newName = "", newPassword = ""))
            every { bookmarkGroupRepository.existsBookmarkGroup(userId = userEntity.id, groupName = groupName) }.returns(false)
            every { bookmarkGroupRepository.createBookmarkGroup(userId = userEntity.id, groupName = groupName) }.returns(bookmarkGroupId)

            // WHEN
            val result = sut.createBookmarkGroup(email = email, groupName = groupName)

            // THEN
            result shouldBe bookmarkGroupId
        }


    }

})
