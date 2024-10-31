package com.management.bookmarkmanagement.bookmarkgroup.controller

import com.management.bookmarkmanagement.bookmarkgroup.application.BookmarkGroupService
import com.management.bookmarkmanagement.bookmarkgroup.dto.NewBookmarkGroupRequest
import com.management.bookmarkmanagement.bookmarkgroup.dto.NewBookmarkGroupResponse
import com.management.bookmarkmanagement.config.TokenHolder
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@Tag(name = "BookmarkGroup API")
@RestController
@RequestMapping("/product/bookmark/v1")
class BookmarkGroupController(
    private val bookmarkGroupService: BookmarkGroupService,
) {

    @PostMapping("/groups")
    @ResponseStatus(HttpStatus.OK)
    fun createBookmarkGroup(@RequestBody request: NewBookmarkGroupRequest): NewBookmarkGroupResponse {
        val email = TokenHolder.getUserEmail()
        val groupId = bookmarkGroupService.createBookmarkGroup(email = email, groupName = request.groupName)
        return NewBookmarkGroupResponse(id = groupId)
    }
}