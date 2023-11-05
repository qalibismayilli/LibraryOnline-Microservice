package com.company.libraryservice.dto

data class BookDto @JvmOverloads constructor(
    val id: BookIdDto? = null,
    val title: String?,
    val bookYear: Int?,
    val authorName: String?,
    val pressName: String,
)
