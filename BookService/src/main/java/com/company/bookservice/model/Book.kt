package com.company.bookservice.model

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id
import jakarta.persistence.Table
import org.hibernate.annotations.GenericGenerator
import kotlin.jvm.JvmOverloads

@Entity
@Table(name = "books")
data class Book @JvmOverloads constructor(
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    val id: String? = "",
    val title: String?,
    val bookYear: Int?,
    val authorName: String?,
    val pressName: String,
    @Column(unique = true)
    val isbn: String?

    )
