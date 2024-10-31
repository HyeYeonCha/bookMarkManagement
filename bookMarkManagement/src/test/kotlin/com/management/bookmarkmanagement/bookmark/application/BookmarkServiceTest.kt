package com.management.bookmarkmanagement.bookmark.application

import com.management.bookmarkmanagement.bookmark.dao.BookmarkRepository
import com.management.bookmarkmanagement.bookmark.dao.ProductJPARepository
import com.management.bookmarkmanagement.bookmark.dto.NewBookmarkDto
import com.management.bookmarkmanagement.bookmark.exception.DuplicatedBookmarkException
import com.management.bookmarkmanagement.bookmarkgroup.domain.ProductEntity
import com.management.bookmarkmanagement.user.application.UserService
import com.management.bookmarkmanagement.user.dao.UserAuthRepository
import com.management.bookmarkmanagement.user.domain.UserEntity
import com.management.bookmarkmanagement.user.exception.NoUserException
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import io.mockk.every
import io.mockk.mockk
import org.springframework.data.repository.findByIdOrNull

class BookmarkServiceTest : FunSpec({

    val userAuthRepository: UserAuthRepository = mockk()
    val userAuthService = UserService(userAuthRepository = userAuthRepository)
    val bookmarkRepository: BookmarkRepository = mockk()
    val productJPARepository: ProductJPARepository = mockk()

    val sut = BookmarkService(
        userService = userAuthService,
        bookmarkRepository = bookmarkRepository,
        productJPARepository = productJPARepository,
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
            every { bookmarkRepository.existsBookmark(userId = userEntity.id, productId = newBookmarkDto.productId) }.returns(true)

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
            val bookmarkId = 1L

            val userEntity = UserEntity(newEmail = newBookmarkDto.email, newName = "", newPassword = "")

            every { userAuthRepository.findByEmail(newBookmarkDto.email) }.returns(userEntity)
            every {
                bookmarkRepository.existsBookmark(
                    userId = userEntity.id,
                    productId = newBookmarkDto.productId
                )
            }.returns(false)

            val productEntity = ProductEntity(id = 1L, name = "test", price = 123, thumbnail = "")
            every { productJPARepository.findByIdOrNull(newBookmarkDto.productId) }.returns(productEntity)

            every {
                bookmarkRepository.createBookmark(
                    userId = userEntity.id,
                    bookmarkGroupId = newBookmarkDto.bookmarkGroupId,
                    productEntity = productEntity
                )
            }.returns(bookmarkId)

            // WHEN
            val result = sut.createBookmark(newBookmarkDto = newBookmarkDto)

            // THEN
            result shouldBe bookmarkId
        }

    }

})
