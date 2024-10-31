package com.management.bookmarkmanagement.bookmark.application

import com.management.bookmarkmanagement.bookmark.dao.BookmarkRepository
import com.management.bookmarkmanagement.bookmark.dao.ProductJPARepository
import com.management.bookmarkmanagement.bookmark.dto.NewBookmarkDto
import com.management.bookmarkmanagement.bookmark.exception.DuplicatedBookmarkException
import com.management.bookmarkmanagement.user.application.UserService
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class BookmarkService(
    val userService: UserService,
    val bookmarkRepository: BookmarkRepository,
    val productJPARepository: ProductJPARepository,
) {
    fun createBookmark(newBookmarkDto: NewBookmarkDto): Long {
        val userId = userService.getUserByEmail(email = newBookmarkDto.email).id

        bookmarkRepository.existsBookmark(userId = userId, productId = newBookmarkDto.productId).run {
            if (this) throw DuplicatedBookmarkException()
        }

        val productEntity = productJPARepository.findByIdOrNull(newBookmarkDto.productId) ?: throw IllegalArgumentException()

        return bookmarkRepository.createBookmark(
            userId = userId,
            bookmarkGroupId = newBookmarkDto.bookmarkGroupId,
            productEntity = productEntity,
        )
    }
}