package com.management.bookmarkmanagement.bookmarkgroup.dto

data class PaginationBookmarkGroups(
    val totalPage: Int,
    val totalItems: Long,
    val items: List<BookmarkGroup>
)
