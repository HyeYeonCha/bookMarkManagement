package com.management.bookmarkmanagement.bookmarkgroup.application

import com.management.bookmarkmanagement.bookmarkgroup.dao.BookmarkGroupRepository
import com.management.bookmarkmanagement.user.application.UserService
import org.springframework.stereotype.Service

@Service
class BookmarkGroupService(
    private val bookmarkGroupRepository: BookmarkGroupRepository,
    private val userService: UserService,
) {
    fun createBookmarkGroup(email: String, groupName: String): Long {

        // user email 로 userId 검색

        // userId 와 groupName 으로 기존 bookmarkGroup 검색

        // 있으면 exception

        // 없으면 저장

        return 1L
    }
}