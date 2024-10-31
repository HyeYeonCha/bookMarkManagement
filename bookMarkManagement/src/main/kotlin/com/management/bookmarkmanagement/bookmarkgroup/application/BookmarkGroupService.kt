package com.management.bookmarkmanagement.bookmarkgroup.application

import com.management.bookmarkmanagement.bookmarkgroup.dao.BookmarkGroupRepository
import com.management.bookmarkmanagement.bookmarkgroup.dto.BookmarkGroup
import com.management.bookmarkmanagement.bookmarkgroup.dto.PaginationBookmarkGroups
import com.management.bookmarkmanagement.bookmarkgroup.dto.PaginationRequest
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

    fun getMyBookmarkGroups(email: String, paginationRequest: PaginationRequest): PaginationBookmarkGroups {
        val userId = userService.getUserByEmail(email = email).id
        val pagedBookmarkGroups = bookmarkGroupRepository.getMyBookmarkGroups(userId = userId, page = paginationRequest.page, pageSize = paginationRequest.pageSize)

        return PaginationBookmarkGroups(
            totalPage = pagedBookmarkGroups.totalPages,
            totalItems = pagedBookmarkGroups.totalElements,
            items = pagedBookmarkGroups.content.map { it.toDto() }
        )
    }
}