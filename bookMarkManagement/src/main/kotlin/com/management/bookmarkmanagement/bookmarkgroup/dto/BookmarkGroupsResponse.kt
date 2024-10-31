package com.management.bookmarkmanagement.bookmarkgroup.dto

data class BookmarkGroupsResponse(
    val totalPage: Int,
    val totalItems: Long,
    val bookmarkGroups: List<BookmarkGroupResponse>
) {
    data class BookmarkGroupResponse(
        val id: Long,
        val groupName: String,
        val thumbnail: String,
        val createDateTime: String,
    ) {
        companion object {
            fun from(bookmarkGroup: BookmarkGroup) = BookmarkGroupResponse(
                id = bookmarkGroup.id,
                groupName = bookmarkGroup.groupName,
                thumbnail = bookmarkGroup.thumbnail ?: "",
                createDateTime = bookmarkGroup.createdDatetime.toString()
            )
        }
    }
}
