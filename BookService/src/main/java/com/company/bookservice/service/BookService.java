package com.company.bookservice.service;

import com.company.bookservice.dto.BookDto;
import com.company.bookservice.dto.BookIdDto;
import com.company.bookservice.exception.BookNotFoundException;
import com.company.bookservice.repository.BookRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {
    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<BookDto> getAllBook() {
        return bookRepository.findAll().stream().map(from -> BookDto.convert(from)).toList();
    }

    public BookIdDto getByIsbn(String isbn) {
        return bookRepository.getBookByIsbn(isbn)
                .map(book -> new BookIdDto(book.getId(), book.getIsbn()))
                .orElseThrow(() -> new BookNotFoundException("Book could not found by this isbn"));
    }

    public BookDto findBookDetailById(String id) {
        return bookRepository.findById(id)
                .map(from -> BookDto.convert(from))
                .orElseThrow(() -> new BookNotFoundException("Book could not found by this id"));
    }

}
