package com.management.bookmarkmanagement.bookmarkgroup.application

import com.management.bookmarkmanagement.bookmarkgroup.dao.BookmarkGroupRepository
import com.management.bookmarkmanagement.bookmarkgroup.exception.DuplicatedBookmarkGroupException
import com.management.bookmarkmanagement.user.application.UserService
import org.springframework.stereotype.Service

@Service
class BookmarkGroupService(
    private val bookmarkGroupRepository: BookmarkGroupRepository,
    private val userService: UserService,
) {
    fun createBookmarkGroup(email: String, groupName: String): Long {
        val userId = userService.getUserByEmail(email = email).id

        bookmarkGroupRepository.existsBookmarkGroup(userId = userId, groupName = groupName).run {
            if (this) throw DuplicatedBookmarkGroupException()
        }

        return bookmarkGroupRepository.createBookmarkGroup(userId = userId, groupName = groupName)
    }

    fun deleteBookmarkGroup(email: String, bookmarkGroupId: Long) {
        val userId = userService.getUserByEmail(email = email).id
        bookmarkGroupRepository.deleteBookmarkGroup(userId = userId, bookmarkGroupId = bookmarkGroupId)
    }
}