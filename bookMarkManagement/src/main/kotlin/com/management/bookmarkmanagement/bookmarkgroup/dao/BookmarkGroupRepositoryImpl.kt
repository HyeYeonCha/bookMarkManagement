package com.management.bookmarkmanagement.bookmarkgroup.dao

import com.management.bookmarkmanagement.bookmarkgroup.domain.BookmarkGroupEntity
import com.management.bookmarkmanagement.bookmarkgroup.dto.BookmarkGroup
import org.springframework.data.domain.Example
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Repository

@Repository
class BookmarkGroupRepositoryImpl(
    private val bookmarkGroupJPARepository: BookmarkGroupJPARepository,
): BookmarkGroupRepository {
    override fun existsBookmarkGroup(userId: Long, groupName: String): Boolean {
        return bookmarkGroupJPARepository.existsByUserIdAndGroupName(userId = userId, groupName = groupName)
    }

    override fun createBookmarkGroup(userId: Long, groupName: String): Long {
        val bookmarkGroupEntity = BookmarkGroupEntity(createdUserId = userId, newName = groupName)
        bookmarkGroupJPARepository.save(bookmarkGroupEntity)

        return bookmarkGroupEntity.id
    }

    override fun deleteBookmarkGroup(userId: Long, bookmarkGroupId: Long) {
        bookmarkGroupJPARepository.findById(bookmarkGroupId)
            .filter { it.userId == userId }
            .ifPresent { bookmarkGroupJPARepository.delete(it) }
    }

    override fun getMyBookmarkGroups(userId: Long, page: Int, pageSize: Int): Page<BookmarkGroupEntity> {
        val pageRequest = PageRequest.of(page, pageSize)
        return bookmarkGroupJPARepository.findAllByUserId(userId = userId, pageable = pageRequest)
    }
}