package com.management.bookmarkmanagement.bookmarkgroup.dao

import com.management.bookmarkmanagement.bookmarkgroup.domain.BookmarkGroupEntity
import org.springframework.data.jpa.repository.JpaRepository

interface BookmarkGroupJPARepository: JpaRepository<BookmarkGroupEntity, Long>