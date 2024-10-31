package com.management.bookmarkmanagement.bookmarkgroup.dao

interface BookmarkGroupRepository {
    fun existsBookmarkGroup(userId: Long, groupName: String): Boolean
    fun createBookmarkGroup(userId: Long, groupName: String): Long
}