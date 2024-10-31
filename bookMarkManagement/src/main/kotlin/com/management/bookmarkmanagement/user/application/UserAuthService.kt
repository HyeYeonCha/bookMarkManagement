package com.management.bookmarkmanagement.user.application

import com.management.bookmarkmanagement.user.dao.UserAuthRepository
import com.management.bookmarkmanagement.user.dto.NewUser
import com.management.bookmarkmanagement.user.dto.SignUpDto
import org.springframework.stereotype.Service

@Service
class UserAuthService (
    private val userAuthRepository: UserAuthRepository
) {
    fun userSignUp(signUpDto: SignUpDto): NewUser {

        // email 로 동일 유저 검색

        // 유저가 있다면 exception (중복 가입 X)

        // 신규 유저 데이터 생성 (DB 저장)

        // accessToken 발급 (JWT)

        // NewUser 반환

        return NewUser(
            userId = 123, accessToken = "123"
        )
    }
}