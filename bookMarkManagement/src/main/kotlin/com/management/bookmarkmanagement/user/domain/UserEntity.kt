package com.management.bookmarkmanagement.user.domain

import java.time.LocalDateTime
import javax.persistence.*

@Entity
@Table(name = "users")
class UserEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 1L,

    @Transient
    private val newEmail: String,
    @Transient
    private val newPassword: String,
) {

    @Column(nullable = false)
    var email: String = newEmail
            private set

    @Column(nullable = false)
    var password: String = newPassword
        private set

    @Column(name = "created_datetime")
    var createdDateTime: LocalDateTime = LocalDateTime.now()
        private set

    @Column(name = "updated_datetime")
    var updatedDateTime: LocalDateTime = LocalDateTime.now()
        private set

    fun validatePassword(password: String): Boolean {
        return password == this.password
    }
}