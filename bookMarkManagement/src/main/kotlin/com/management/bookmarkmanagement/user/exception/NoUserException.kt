package com.management.bookmarkmanagement.user.exception

const val NO_USER = "존재하지 않는 유저입니다."

data class NoUserException(override val message: String = NO_USER): IllegalArgumentException()
