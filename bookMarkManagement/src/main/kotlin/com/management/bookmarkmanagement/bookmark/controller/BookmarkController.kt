package com.management.bookmarkmanagement.bookmark.controller

import com.management.bookmarkmanagement.bookmark.application.BookmarkService
import com.management.bookmarkmanagement.bookmark.dto.BookmarksResponse
import com.management.bookmarkmanagement.bookmark.dto.DeleteBookmarkDto
import com.management.bookmarkmanagement.bookmark.dto.NewBookmarkRequest
import com.management.bookmarkmanagement.bookmark.dto.NewBookmarkResponse
import com.management.bookmarkmanagement.bookmarkgroup.dto.PaginationRequest
import com.management.bookmarkmanagement.config.TokenHolder
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
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

    @DeleteMapping("/{bookmarkGroupId}/bookmarks/{bookmarkId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deleteBookmark(
        @PathVariable bookmarkGroupId: Long,
        @PathVariable bookmarkId: Long,
    ): ResponseEntity<Void> {
        val email = TokenHolder.getUserEmail()
        val deleteBookmarkDto = DeleteBookmarkDto(email = email, bookmarkGroupId = bookmarkGroupId, bookmarkId = bookmarkId)
        bookmarkService.deleteBookmark(deleteBookmarkDto = deleteBookmarkDto)

        return ResponseEntity(HttpStatus.NO_CONTENT)
    }

    @GetMapping("/{bookmarkGroupId}/bookmarks")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun getBookmarksByGroupId(
        @PathVariable bookmarkGroupId: Long,
        paginationRequest: PaginationRequest
    ): BookmarksResponse {
        val email = TokenHolder.getUserEmail()
        val pagedBookmarks = bookmarkService.getBookmarksByGroupId(email = email, bookmarkGroupId = bookmarkGroupId, paginationRequest = paginationRequest)

        return BookmarksResponse(
            totalPage = pagedBookmarks.totalPage,
            totalItems = pagedBookmarks.totalItems,
            bookmarks = pagedBookmarks.items.map { bookmark -> BookmarksResponse.BookmarkResponse.from(bookmark) }
        )
    }
}