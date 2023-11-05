package com.company.bookservice.dto

import com.company.bookservice.model.Book

data class BookDto @JvmOverloads constructor(

    val id: BookIdDto? = null,
    val title: String?,
    val bookYear: Int?,
    val authorName: String?,
    val pressName: String,
){
    companion object{
        @JvmStatic
        fun convert(from: Book): BookDto{
            return BookDto(
                from.id?.let { BookIdDto.convert(it,from.isbn) },
                from.title,
                from.bookYear,
                from.authorName,
                from.pressName,
            )
        }
    }
}
