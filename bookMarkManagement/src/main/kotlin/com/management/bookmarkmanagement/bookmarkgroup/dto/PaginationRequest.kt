package com.management.bookmarkmanagement.bookmarkgroup.dto

data class PaginationRequest(
    val page: Int = 1,
    val pageSize: Int = 10,
)
