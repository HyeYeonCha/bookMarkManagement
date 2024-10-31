package com.management.bookmarkmanagement.bookmark.application

import com.management.bookmarkmanagement.bookmark.dao.BookmarkRepository
import com.management.bookmarkmanagement.bookmark.dto.NewBookmarkDto
import com.management.bookmarkmanagement.bookmark.exception.DuplicatedBookmarkException
import com.management.bookmarkmanagement.user.application.UserService
import com.management.bookmarkmanagement.user.dao.UserAuthRepository
import com.management.bookmarkmanagement.user.domain.UserEntity
import com.management.bookmarkmanagement.user.exception.NoUserException
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import io.mockk.every
import io.mockk.mockk

class BookmarkServiceTest : FunSpec({

    val bookmarkRepository: BookmarkRepository = mockk()
    val userAuthRepository: UserAuthRepository = mockk()
    val userAuthService = UserService(userAuthRepository = userAuthRepository)

    val sut = BookmarkService(
        userService = userAuthService,
        bookmarkRepository = bookmarkRepository,
    )

    context("createBookmark") {
        test("email 로 user 를 찾았을 때 user 가 없다면 NoUserException 이 발생한다.") {
            // GIVEN
            val newBookmarkDto = NewBookmarkDto(
                email = "testEmail",
                productId = 1L,
                bookmarkGroupId = 1L
            )

            every { userAuthRepository.findByEmail(newBookmarkDto.email) }.returns(null)

            // WHEN && THEN
            val exception = shouldThrow<NoUserException> {
                sut.createBookmark(newBookmarkDto = newBookmarkDto)
            }

            exception.message shouldBe "존재하지 않는 유저입니다."
        }

        test("userId 와 productId 으로 bookmark 를 찾았을 때 bookmark 있다면 DuplicatedBookmarkException 이 발생한다.") {
            // GIVEN
            val newBookmarkDto = NewBookmarkDto(
                email = "testEmail",
                productId = 1L,
                bookmarkGroupId = 1L
            )
            val userEntity = UserEntity(newEmail = newBookmarkDto.email, newName = "", newPassword = "")

            every { userAuthRepository.findByEmail(newBookmarkDto.email) }.returns(userEntity)
            // bookmark 검색

            // WHEN && THEN
            val exception = shouldThrow<DuplicatedBookmarkException> {
                sut.createBookmark(newBookmarkDto = newBookmarkDto)
            }

            exception.message shouldBe "다른 찜서랍에 해당 상품이 존재합니다."
        }

        test("중복되지 않은 찜이라면 저장 후 찜 ID 를 반환한다.") {
            // GIVEN
            val newBookmarkDto = NewBookmarkDto(
                email = "testEmail",
                productId = 1L,
                bookmarkGroupId = 1L
            )
            val userEntity = UserEntity(newEmail = newBookmarkDto.email, newName = "", newPassword = "")

            every { userAuthRepository.findByEmail(newBookmarkDto.email) }.returns(userEntity)
            // bookmark 검색
            // bookmark 저장


            // WHEN
            val result = sut.createBookmark(newBookmarkDto = newBookmarkDto)

            // THEN
            result shouldBe 1L
        }


    }

})
