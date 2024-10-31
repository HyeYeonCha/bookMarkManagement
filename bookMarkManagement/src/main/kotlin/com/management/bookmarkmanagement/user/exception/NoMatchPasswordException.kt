package com.management.bookmarkmanagement.user.exception

const val NO_MATCH_PASSWORD = "비밀번호가 일치하지 않습니다."

data class NoMatchPasswordException(override val message: String = NO_MATCH_PASSWORD): IllegalArgumentException()
