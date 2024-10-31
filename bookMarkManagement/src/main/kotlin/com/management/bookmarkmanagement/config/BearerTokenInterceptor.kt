package com.management.bookmarkmanagement.config

import com.management.bookmarkmanagement.jwt.JwtUtil
import org.springframework.stereotype.Component
import org.springframework.web.servlet.HandlerInterceptor
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Component
class BearerTokenInterceptor: HandlerInterceptor {

    override fun preHandle(request: HttpServletRequest, response: HttpServletResponse, handler: Any): Boolean {
        val authHeader = request.getHeader("Authorization")

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Authorization header is missing or invalid")
            return false
        }

        val token: String = authHeader.removePrefix("Bearer ")
        TokenHolder.setToken(token)

        return true
    }
}

object TokenHolder {
    private val jwtUtil = JwtUtil()

    private lateinit var token: String

    fun setToken(token: String) {
        this.token = token
    }

    private fun getToken(): String {
        return this.token
    }

    fun getUserEmail(): String = jwtUtil.extractUserEmail(this.getToken())
}