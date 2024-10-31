package com.management.bookmarkmanagement.bookmarkgroup.dao

import com.management.bookmarkmanagement.bookmarkgroup.domain.BookmarkGroupEntity
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository

interface BookmarkGroupJPARepository: JpaRepository<BookmarkGroupEntity, Long> {
    fun findAllByUserId(userId: Long, pageable: Pageable): Page<BookmarkGroupEntity>
    fun existsByUserIdAndGroupName(userId: Long, groupName: String): Boolean
}