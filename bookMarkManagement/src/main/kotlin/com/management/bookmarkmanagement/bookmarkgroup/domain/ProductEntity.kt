package com.management.bookmarkmanagement.bookmarkgroup.domain

import java.time.LocalDateTime
import javax.persistence.*

@Table(name = "products")
@Entity
class ProductEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 1L,

    @Column(name = "name")
    val name: String,

    @Column(name = "price")
    val price: Long,

    @Column(name = "thumbnail")
    val thumbnail: String,

    @Column(name = "created_datetime")
    val createdDateTime: LocalDateTime = LocalDateTime.now(),

    @Column(name = "updated_datetime")
    val updatedDateTime: LocalDateTime = LocalDateTime.now()
)