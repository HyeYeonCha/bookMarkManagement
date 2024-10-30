package com.management.bookmarkmanagement.user.controller
import com.management.bookmarkmanagement.user.request.SignUpRequest
import com.management.bookmarkmanagement.user.response.SignUpResponse
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@Tag(name = "User-Auth API")
@RestController
@RequestMapping("/user/auth/v1")
class UserAuthController {

    @PostMapping("/sign-up")
    @ResponseStatus(HttpStatus.OK)
    fun signUp(@RequestBody request: SignUpRequest): SignUpResponse {
        return SignUpResponse(userId = 123, accessToken = "123")
    }
}