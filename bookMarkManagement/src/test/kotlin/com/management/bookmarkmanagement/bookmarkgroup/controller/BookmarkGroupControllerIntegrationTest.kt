package com.management.bookmarkmanagement.bookmarkgroup.controller

import com.fasterxml.jackson.databind.ObjectMapper
import com.management.bookmarkmanagement.bookmarkgroup.dto.NewBookmarkGroupRequest
import com.management.bookmarkmanagement.jwt.JwtUtil
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
class BookmarkGroupControllerIntegrationTest {

    @Autowired
    private lateinit var mockMvc: MockMvc

    @Autowired
    private lateinit var objectMapper: ObjectMapper

    @Test
    fun testCreateBookmarkGroup() {
        val bookmarkGroupRequest = NewBookmarkGroupRequest(
            groupName = "testBookmarkGroupName",
        )

        val testEmail = "test@test.com"
        val testToken = JwtUtil().generateToken(testEmail)

        val result = mockMvc.perform(
            post("/product/bookmark/v1/groups")
                .contentType(APPLICATION_JSON)
                .header("Authorization", "Bearer $testToken")
                .content(objectMapper.writeValueAsString(bookmarkGroupRequest))
        )
            .andExpect(status().isOk)
            .andReturn()

        val responseStatus = result.response.status

        responseStatus shouldBe 200
    }
}
