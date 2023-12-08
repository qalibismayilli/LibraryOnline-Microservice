package com.company.libraryservice.dto

data class BookDto @JvmOverloads constructor(
    val id: BookIdDto? = null,
    val title: String? = null,
    val bookYear: Int? = null,
    val authorName: String? = null,
    val pressName: String? = null,
)
