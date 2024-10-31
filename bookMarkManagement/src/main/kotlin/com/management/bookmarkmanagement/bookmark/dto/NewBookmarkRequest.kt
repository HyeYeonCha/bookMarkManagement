package com.management.bookmarkmanagement.bookmark.dto

data class NewBookmarkRequest(
    val productId: Long
) {
    fun toDto(email: String, bookmarkGroupId: Long) = NewBookmarkDto(
        productId = productId,
        email = email,
        bookmarkGroupId = bookmarkGroupId,
    )
}
