package com.management.bookmarkmanagement.bookmark.controller

import com.fasterxml.jackson.databind.ObjectMapper
import com.management.bookmarkmanagement.bookmark.dto.NewBookmarkRequest
import com.management.bookmarkmanagement.jwt.JwtUtil
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType.APPLICATION_JSON
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@SpringBootTest
@AutoConfigureMockMvc
class BookmarkControllerTest {

    @Autowired
    private lateinit var mockMvc: MockMvc

    @Autowired
    private lateinit var objectMapper: ObjectMapper


    @Test
    fun testCreateBookmark() {
        val bookmarkRequest = NewBookmarkRequest(
            productId = 1L
        )

        val testEmail = "test@test.com"
        val testToken = JwtUtil().generateToken(testEmail)

        val result = mockMvc.perform(
            post("/product/bookmark/v1/groups/1/bookmarks")
                .contentType(APPLICATION_JSON)
                .header("Authorization", "Bearer $testToken")
                .content(objectMapper.writeValueAsString(bookmarkRequest))
        )
            .andExpect(status().isOk)
            .andReturn()

        val responseStatus = result.response.status

        responseStatus shouldBe 200
    }
}

