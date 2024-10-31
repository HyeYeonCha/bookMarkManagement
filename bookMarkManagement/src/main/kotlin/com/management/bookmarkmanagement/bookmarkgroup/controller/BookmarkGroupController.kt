package com.management.bookmarkmanagement.bookmarkgroup.controller

import com.management.bookmarkmanagement.bookmarkgroup.dto.NewBookmarkGroupRequest
import com.management.bookmarkmanagement.bookmarkgroup.dto.NewBookmarkGroupResponse
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@Tag(name = "BookmarkGroup API")
@RestController
@RequestMapping("/product/bookmark/v1")
class BookmarkGroupController {

    @PostMapping("/groups")
    @ResponseStatus(HttpStatus.OK)
    fun createBookmarkGroup(@RequestBody request: NewBookmarkGroupRequest): NewBookmarkGroupResponse {

        return NewBookmarkGroupResponse(id = 1L)
    }
}