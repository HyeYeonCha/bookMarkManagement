package com.management.bookmarkmanagement.user.controller

import com.fasterxml.jackson.databind.ObjectMapper
import com.management.bookmarkmanagement.user.dto.SignUpRequest
import io.kotest.matchers.shouldBe
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType.APPLICATION_JSON
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import kotlin.test.Test

@SpringBootTest
@AutoConfigureMockMvc
class UserAuthControllerIntegrationTest {

    @Autowired
    private lateinit var mockMvc: MockMvc

    @Autowired
    private lateinit var objectMapper: ObjectMapper

    @Test
    fun testSignUp() {
        val signUpRequest = SignUpRequest(
            email = "test11@test.com", password = "asdf1234"
        )

        val result = mockMvc.perform(
            post("/user/auth/v1/sign-up")
                .contentType(APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(signUpRequest))
        )
            .andExpect(status().isOk)
            .andReturn()

        val responseStatus = result.response.status

        responseStatus shouldBe 200
    }


    @Test
    fun testSignIn() {
        val signUpRequest = SignUpRequest(
            email = "test@test.com", password = "test1234"
        )

        val result = mockMvc.perform(
            post("/user/auth/v1/sign-in")
                .contentType(APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(signUpRequest))
        )
            .andExpect(status().isOk)
            .andReturn()

        val responseStatus = result.response.status

        responseStatus shouldBe 200
    }
}
