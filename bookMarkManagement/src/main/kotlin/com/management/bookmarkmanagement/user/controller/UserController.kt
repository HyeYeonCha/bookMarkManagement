package com.management.bookmarkmanagement.user.controller

import com.management.bookmarkmanagement.config.TokenHolder
import com.management.bookmarkmanagement.user.application.UserService
import com.management.bookmarkmanagement.user.dto.UserResponse
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@Tag(name = "User API")
@RestController
@RequestMapping("/user/v1")
class UserController(
    private val userService: UserService
) {

    @GetMapping("/{userId}")
    @ResponseStatus(HttpStatus.OK)
    fun getUser(
        @PathVariable userId: Long
    ): UserResponse {
        val email = TokenHolder.getUserEmail()
        val user = userService.getUserByEmail(email = email)

        return UserResponse(
            id = user.id,
            email = user.email,
            name = user.userName,
            createdDateTime = user.createdDateTime.toString(),
        )
    }
}