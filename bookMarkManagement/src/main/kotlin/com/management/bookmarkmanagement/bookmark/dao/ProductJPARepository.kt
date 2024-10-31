package com.management.bookmarkmanagement.bookmark.dao

import com.management.bookmarkmanagement.bookmarkgroup.domain.ProductEntity
import org.springframework.data.jpa.repository.JpaRepository

interface ProductJPARepository: JpaRepository<ProductEntity, Long>