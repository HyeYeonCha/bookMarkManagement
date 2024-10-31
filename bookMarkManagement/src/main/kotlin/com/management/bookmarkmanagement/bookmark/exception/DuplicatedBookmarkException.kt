package com.management.bookmarkmanagement.bookmark.exception

const val DUPLICATED_BOOKMARK_EXCEPTION = "다른 찜서랍에 해당 상품이 존재합니다."

data class DuplicatedBookmarkException(override val message: String = DUPLICATED_BOOKMARK_EXCEPTION) :
    IllegalArgumentException(message)
