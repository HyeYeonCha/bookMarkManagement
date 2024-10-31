package com.management.bookmarkmanagement.bookmark.dto

import java.time.LocalDateTime

data class Bookmark(
    val id: Long,
    val userId: Long,
    val productName: String,
    val productThumbnail: String,
    val createdDatetime: LocalDateTime,
)
