package com.management.bookmarkmanagement.bookmarkgroup.dto

import java.time.LocalDateTime

data class BookmarkGroup(
    val id: Long,
    val userId: Long,
    val groupName: String,
    val thumbnail: String?,
    val createdDatetime: LocalDateTime,
)
