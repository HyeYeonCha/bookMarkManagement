package com.management.bookmarkmanagement.user.controller

import com.management.bookmarkmanagement.config.TokenHolder
import com.management.bookmarkmanagement.user.dto.UserResponse
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import java.time.LocalDateTime

@Tag(name = "User API")
@RestController
@RequestMapping("/user/v1")
class UserController {

    @GetMapping("/{userId}")
    @ResponseStatus(HttpStatus.OK)
    fun getUser(
        @PathVariable userId: Long
    ): UserResponse {
        val email = TokenHolder.getUserEmail()

        return UserResponse(
            id = 1L,
            email = email,
            name = "",
            createdDateTime = LocalDateTime.now().toString()
        )
    }
}