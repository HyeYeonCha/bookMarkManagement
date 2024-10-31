package com.management.bookmarkmanagement.user.controller
import com.management.bookmarkmanagement.user.application.UserAuthService
import com.management.bookmarkmanagement.user.dto.SignUpRequest
import com.management.bookmarkmanagement.user.dto.SignUpResponse
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@Tag(name = "User-Auth API")
@RestController
@RequestMapping("/user/auth/v1")
class UserAuthController(
    private val userAuthService: UserAuthService
) {

    @PostMapping("/sign-up")
    @ResponseStatus(HttpStatus.OK)
    fun signUp(@RequestBody request: SignUpRequest): SignUpResponse {
        val newUser = userAuthService.userSignUp(
            signUpDto = request.toDto()
        )

        return SignUpResponse(userId = newUser.userId, accessToken = newUser.accessToken)
    }
}