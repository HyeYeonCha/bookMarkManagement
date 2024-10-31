package com.management.bookmarkmanagement.bookmark.dto

data class NewBookmarkDto(
    val email: String,
    val productId: Long,
    val bookmarkGroupId: Long,
)