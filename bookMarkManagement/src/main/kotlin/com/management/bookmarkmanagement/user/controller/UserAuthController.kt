package com.management.bookmarkmanagement.user.controller
import com.management.bookmarkmanagement.user.application.UserAuthService
import com.management.bookmarkmanagement.user.dto.SignInRequest
import com.management.bookmarkmanagement.user.dto.SignUpRequest
import com.management.bookmarkmanagement.user.dto.AuthUserResponse
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
    fun signUp(@RequestBody request: SignUpRequest): AuthUserResponse {
        val newUser = userAuthService.userSignUp(
            signUpDto = request.toDto()
        )

        return AuthUserResponse(userId = newUser.userId, accessToken = newUser.accessToken)
    }

    @PostMapping("/sign-in")
    @ResponseStatus(HttpStatus.OK)
    fun signIn(@RequestBody request: SignInRequest): AuthUserResponse {
        val user = userAuthService.userSignIn(
            signInDto = request.toDto()
        )

        return AuthUserResponse(userId = user.userId, accessToken = user.accessToken)
    }
}