package com.management.bookmarkmanagement.user.dao

import com.management.bookmarkmanagement.user.domain.UserEntity
import org.springframework.data.jpa.repository.JpaRepository

interface UserJPARepository: JpaRepository<UserEntity, Long> {
    fun findByEmail(email: String): UserEntity?
}