package com.management.bookmarkmanagement.bookmark.dto

data class DeleteBookmarkDto(
    val email: String,
    val bookmarkGroupId: Long,
    val bookmarkId: Long,
)
