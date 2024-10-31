package com.management.bookmarkmanagement.bookmarkgroup.domain

import com.management.bookmarkmanagement.bookmarkgroup.dto.BookmarkGroup
import java.time.LocalDateTime
import javax.persistence.*

@Table(name = "bookmark_groups")
@Entity
class BookmarkGroupEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 1L,

    @Transient
    private val createdUserId: Long,
    @Transient
    private val newName: String,
) {
    @Column(name = "user_id", nullable = false)
    var userId: Long = createdUserId
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


    fun toDto() = BookmarkGroup(
        id = this.id,
        userId = this.userId,
        groupName = this.groupName,
        thumbnail = null,
        createdDatetime = this.createdDateTime,
    )
}