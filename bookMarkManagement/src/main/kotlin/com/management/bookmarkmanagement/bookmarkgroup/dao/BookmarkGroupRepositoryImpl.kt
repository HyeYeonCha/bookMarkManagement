package com.management.bookmarkmanagement.bookmarkgroup.dao

import org.springframework.stereotype.Repository

@Repository
class BookmarkGroupRepositoryImpl(
    private val bookmarkGroupJPARepository: BookmarkGroupJPARepository,
): BookmarkGroupRepository {
}