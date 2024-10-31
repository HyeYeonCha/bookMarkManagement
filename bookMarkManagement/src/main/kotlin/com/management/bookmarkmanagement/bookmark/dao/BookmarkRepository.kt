package com.management.bookmarkmanagement.bookmark.dao

import com.management.bookmarkmanagement.bookmarkgroup.domain.ProductEntity

interface BookmarkRepository {
    fun existsBookmark(userId: Long, productId: Long): Boolean
    fun createBookmark(userId: Long, bookmarkGroupId: Long, productEntity: ProductEntity): Long
}