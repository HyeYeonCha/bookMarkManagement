package com.management.bookmarkmanagement.bookmark.dao

import com.management.bookmarkmanagement.bookmark.domain.BookmarkEntity
import com.management.bookmarkmanagement.bookmark.dto.DeleteBookmarkDto
import com.management.bookmarkmanagement.bookmarkgroup.domain.ProductEntity
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
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

    override fun deleteBookmark(userId: Long, deleteBookmarkDto: DeleteBookmarkDto) {
        bookmarkJPARepository.findById(deleteBookmarkDto.bookmarkId)
            .filter {
                it.userId == userId && it.bookmarkGroupId == deleteBookmarkDto.bookmarkGroupId
            }
            .ifPresent { bookmark -> bookmarkJPARepository.deleteById(bookmark.id) }
    }

    override fun getMyBookmarks(userId: Long, bookmarkGroupId: Long, page: Int, pageSize: Int): Page<BookmarkEntity> {
        val pageRequest = PageRequest.of(page, pageSize)
        return bookmarkJPARepository.findAllByUserIdAndBookmarkGroupId(
            userId = userId,
            bookmarkGroupId = bookmarkGroupId,
            pageable = pageRequest
        )
    }
}