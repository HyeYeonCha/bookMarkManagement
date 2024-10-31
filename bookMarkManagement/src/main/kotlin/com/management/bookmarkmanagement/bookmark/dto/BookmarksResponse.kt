package com.management.bookmarkmanagement.bookmark.dto

data class BookmarksResponse(
    val totalPage: Int,
    val totalItems: Long,
    val bookmarks: List<BookmarkResponse>
) {
    data class BookmarkResponse(
        val id: Long,
        val productName: String,
        val productThumbnail: String,
        val createdDateTime: String,
    ) {
        companion object {
            fun from(bookmark: Bookmark) = BookmarkResponse(
                id = bookmark.id,
                productName = bookmark.productName,
                productThumbnail = bookmark.productThumbnail,
                createdDateTime = bookmark.createdDatetime.toString(),
            )
        }
    }
}
