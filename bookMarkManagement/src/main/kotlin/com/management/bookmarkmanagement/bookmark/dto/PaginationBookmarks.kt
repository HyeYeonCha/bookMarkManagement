package com.management.bookmarkmanagement.bookmark.dto

data class PaginationBookmarks(
    val totalPage: Int,
    val totalItems: Long,
    val items: List<Bookmark>
)
