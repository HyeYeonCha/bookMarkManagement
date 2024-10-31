package com.management.bookmarkmanagement.bookmark.dao

import com.management.bookmarkmanagement.bookmark.domain.BookmarkEntity
import org.springframework.data.jpa.repository.JpaRepository

interface BookmarkJPARepository: JpaRepository<BookmarkEntity, Long> {
    fun existsByUserIdAndProductId(userId: Long, productId: Long): Boolean
}