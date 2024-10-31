package com.management.bookmarkmanagement.bookmarkgroup.domain

import java.time.LocalDateTime
import javax.persistence.*

@Table(name = "bookmark_groups")
@Entity
class BookmarkGroupEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 1L,

    @Transient
    private val createdBy: Long,
    @Transient
    private val newName: String,
) {
    @Column(name = "user_id", nullable = false)
    var userId: Long = createdBy
        private set

    @Column(name = "group_name", nullable = false)
    var groupName: String = newName
        private set

    @Column(name = "created_datetime")
    var createdDateTime: LocalDateTime = LocalDateTime.now()
        private set

    @Column(name = "updated_datetime")
    var updatedDateTime: LocalDateTime = LocalDateTime.now()
        private set
}