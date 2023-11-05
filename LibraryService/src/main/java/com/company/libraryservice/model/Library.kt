package com.company.libraryservice.model

import jakarta.persistence.*
import org.hibernate.annotations.GenericGenerator

@Entity
@Table(name = "libraries")
data class Library @JvmOverloads constructor(
    @Id
    @Column(name = "library_id")
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID" , strategy = "org.hibernate.id.UUIDGenerator")
    val id: String? = "",

    @ElementCollection
    val userBook: List<String> = ArrayList()


)
