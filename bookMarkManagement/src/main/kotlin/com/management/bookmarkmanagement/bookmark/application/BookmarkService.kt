package com.management.bookmarkmanagement.bookmark.application

import com.management.bookmarkmanagement.bookmark.dao.BookmarkRepository
import com.management.bookmarkmanagement.bookmark.dto.NewBookmarkDto
import com.management.bookmarkmanagement.user.application.UserService
import org.springframework.stereotype.Service

@Service
class BookmarkService(
    val userService: UserService,
    val bookmarkRepository: BookmarkRepository,
) {
    fun createBookmark(newBookmarkDto: NewBookmarkDto): Long {
        // userId 가져오기

        // userId 와 productId 로 bookmark table 에 있는지 조회

        // 있으면 exception

        // 없으면 create

        return 1L
    }
}