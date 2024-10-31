package com.management.bookmarkmanagement.bookmark.domain

import com.management.bookmarkmanagement.bookmarkgroup.domain.ProductEntity
import java.time.LocalDateTime
import javax.persistence.*

@Table(name = "bookmarks")
@Entity
class BookmarkEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 1L,

    @Transient
    private val createdBookmarkGroupId: Long,

    @Transient
    private val createdUserId: Long,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    val product: ProductEntity,
) {
    @Column(name = "user_id", nullable = false)
    var userId: Long = createdUserId
        private set

    @Column(name = "bookmark_group_id", nullable = false)
    var bookmarkGroupId: Long = createdBookmarkGroupId
        private set

    @Column(name = "created_datetime")
    var createdDateTime: LocalDateTime = LocalDateTime.now()
        private set

    @Column(name = "updated_datetime")
    var updatedDateTime: LocalDateTime = LocalDateTime.now()
        private set
}