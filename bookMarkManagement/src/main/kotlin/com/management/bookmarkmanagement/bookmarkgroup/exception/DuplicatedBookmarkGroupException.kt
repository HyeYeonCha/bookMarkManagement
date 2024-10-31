package com.management.bookmarkmanagement.bookmarkgroup.exception

const val DUPLICATED_BOOKMARK_GROUP_EXCEPTION = "이미 존재하는 찜서랍입니다."

data class DuplicatedBookmarkGroupException(override val message: String = DUPLICATED_BOOKMARK_GROUP_EXCEPTION) :
    IllegalArgumentException(message)
