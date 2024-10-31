package com.management.bookmarkmanagement.bookmark.controller

import com.management.bookmarkmanagement.bookmark.application.BookmarkService
import com.management.bookmarkmanagement.bookmark.dto.NewBookmarkDto
import com.management.bookmarkmanagement.bookmark.dto.NewBookmarkRequest
import com.management.bookmarkmanagement.bookmark.dto.NewBookmarkResponse
import com.management.bookmarkmanagement.config.TokenHolder
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@Tag(name = "Bookmark API")
@RestController
@RequestMapping("/product/bookmark/v1/groups")
class BookmarkController(
    private val bookmarkService: BookmarkService,
) {

    @PostMapping("/{bookmarkGroupId}/bookmarks")
    @ResponseStatus(HttpStatus.OK)
    fun createBookmark(
        @PathVariable bookmarkGroupId: Long,
        @RequestBody request: NewBookmarkRequest,
    ): NewBookmarkResponse {
        val email = TokenHolder.getUserEmail()
        val newBookmarkDto = request.toDto(email = email, bookmarkGroupId = bookmarkGroupId)

        val bookmarkId = bookmarkService.createBookmark(newBookmarkDto = newBookmarkDto)

        return NewBookmarkResponse(id = bookmarkId)
    }
}