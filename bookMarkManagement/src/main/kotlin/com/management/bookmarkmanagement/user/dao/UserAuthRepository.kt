package com.management.bookmarkmanagement.user.dao

import com.management.bookmarkmanagement.user.domain.UserEntity
import com.management.bookmarkmanagement.user.dto.SignUpDto

interface UserAuthRepository {
    fun findByEmail(email: String): UserEntity?
    fun saveNewUser(signUpDto: SignUpDto): Long
}