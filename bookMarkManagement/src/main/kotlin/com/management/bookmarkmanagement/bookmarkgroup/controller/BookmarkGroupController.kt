package com.management.bookmarkmanagement.bookmarkgroup.controller

import com.management.bookmarkmanagement.bookmarkgroup.application.BookmarkGroupService
import com.management.bookmarkmanagement.bookmarkgroup.dto.*
import com.management.bookmarkmanagement.config.TokenHolder
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
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

    @DeleteMapping("/groups/{bookmarkGroupId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deleteBookmarkGroup(@PathVariable bookmarkGroupId: Long): ResponseEntity<Void> {
        val email = TokenHolder.getUserEmail()
        bookmarkGroupService.deleteBookmarkGroup(email = email, bookmarkGroupId = bookmarkGroupId)

        return ResponseEntity(HttpStatus.NO_CONTENT)
    }


    @GetMapping("/groups")
    @ResponseStatus(HttpStatus.OK)
    fun getBookmarkGroups(paginationRequest: PaginationRequest): BookmarkGroupsResponse {
        val email = TokenHolder.getUserEmail()
        val pagedBookmarkGroups = bookmarkGroupService.getMyBookmarkGroups(email = email, paginationRequest = paginationRequest)

        return BookmarkGroupsResponse(
            totalPage = pagedBookmarkGroups.totalPage,
            totalItems = pagedBookmarkGroups.totalItems,
            bookmarkGroups = pagedBookmarkGroups.items.map { BookmarkGroupsResponse.BookmarkGroupResponse.from(it) }
        )
    }
}