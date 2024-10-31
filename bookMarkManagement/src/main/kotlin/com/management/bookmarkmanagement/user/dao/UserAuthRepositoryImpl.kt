package com.management.bookmarkmanagement.user.dao

import com.management.bookmarkmanagement.user.domain.UserEntity
import com.management.bookmarkmanagement.user.dto.SignUpDto
import org.springframework.stereotype.Repository

@Repository
class UserAuthRepositoryImpl(
    private val userJPARepository: UserJPARepository,
): UserAuthRepository {
    override fun findByEmail(email: String): UserEntity? {
        return userJPARepository.findByEmail(email = email)
    }

    override fun saveNewUser(signUpDto: SignUpDto): Long {
        val userEntity = UserEntity(
            newEmail = signUpDto.email,
            newName = signUpDto.name,
            newPassword = signUpDto.password,
        )

        userJPARepository.save(userEntity)
        return userEntity.id
    }
}