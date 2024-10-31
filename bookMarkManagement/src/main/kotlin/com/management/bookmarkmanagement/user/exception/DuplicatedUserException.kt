package com.management.bookmarkmanagement.user.exception

const val DUPLICATED_USER = "중복 유저로 가입불가"

data class DuplicatedUserException(override val message: String = DUPLICATED_USER) : IllegalArgumentException()
