package com.management.bookmarkmanagement.bookmarkgroup.dao

import com.management.bookmarkmanagement.bookmarkgroup.domain.BookmarkGroupEntity
import org.springframework.data.domain.Example
import org.springframework.stereotype.Repository

@Repository
class BookmarkGroupRepositoryImpl(
    private val bookmarkGroupJPARepository: BookmarkGroupJPARepository,
): BookmarkGroupRepository {
    override fun existsBookmarkGroup(userId: Long, groupName: String): Boolean {
        val bookmarkGroupExample = Example.of(BookmarkGroupEntity(createdUserId = userId, newName = groupName))
        return bookmarkGroupJPARepository.exists(bookmarkGroupExample)
    }

    override fun createBookmarkGroup(userId: Long, groupName: String): Long {
        val bookmarkGroupEntity = BookmarkGroupEntity(createdUserId = userId, newName = groupName)
        bookmarkGroupJPARepository.save(bookmarkGroupEntity)

        return bookmarkGroupEntity.id
    }
}