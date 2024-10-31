package com.management.bookmarkmanagement.bookmark.dao

import com.management.bookmarkmanagement.bookmark.domain.BookmarkEntity
import com.management.bookmarkmanagement.bookmark.dto.DeleteBookmarkDto
import com.management.bookmarkmanagement.bookmarkgroup.domain.ProductEntity
import org.springframework.data.domain.Page

interface BookmarkRepository {
    fun existsBookmark(userId: Long, productId: Long): Boolean
    fun createBookmark(userId: Long, bookmarkGroupId: Long, productEntity: ProductEntity): Long
    fun deleteBookmark(userId: Long, deleteBookmarkDto: DeleteBookmarkDto)
    fun getMyBookmarks(userId: Long, bookmarkGroupId: Long, page: Int, pageSize: Int): Page<BookmarkEntity>
}