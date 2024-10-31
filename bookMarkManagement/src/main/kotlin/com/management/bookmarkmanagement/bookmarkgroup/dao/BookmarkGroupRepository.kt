package com.management.bookmarkmanagement.bookmarkgroup.dao

import com.management.bookmarkmanagement.bookmarkgroup.domain.BookmarkGroupEntity
import org.springframework.data.domain.Page

interface BookmarkGroupRepository {
    fun existsBookmarkGroup(userId: Long, groupName: String): Boolean
    fun createBookmarkGroup(userId: Long, groupName: String): Long
    fun deleteBookmarkGroup(userId: Long, bookmarkGroupId: Long)
    fun getMyBookmarkGroups(userId: Long, page: Int, pageSize: Int): Page<BookmarkGroupEntity>
}