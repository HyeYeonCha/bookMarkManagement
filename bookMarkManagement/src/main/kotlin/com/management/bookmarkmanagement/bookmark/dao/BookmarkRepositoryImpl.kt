package com.management.bookmarkmanagement.bookmark.dao

import com.management.bookmarkmanagement.bookmark.domain.BookmarkEntity
import com.management.bookmarkmanagement.bookmarkgroup.domain.ProductEntity
import org.springframework.stereotype.Repository

@Repository
class BookmarkRepositoryImpl(
    private val bookmarkJPARepository: BookmarkJPARepository
): BookmarkRepository {
    override fun existsBookmark(userId: Long, productId: Long): Boolean {
        return bookmarkJPARepository.existsByUserIdAndProductId(userId = userId, productId = productId)
    }

    override fun createBookmark(userId: Long, bookmarkGroupId: Long, productEntity: ProductEntity): Long {
        val newBookmarkEntity = BookmarkEntity(createdUserId = userId, createdBookmarkGroupId = bookmarkGroupId, product = productEntity)
        bookmarkJPARepository.save(newBookmarkEntity)
        return newBookmarkEntity.id
    }
}