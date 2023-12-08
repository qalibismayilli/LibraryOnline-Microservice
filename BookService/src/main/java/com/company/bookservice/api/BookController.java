package com.company.bookservice.api;

import com.company.bookservice.dto.BookDto;
import com.company.bookservice.dto.BookIdDto;
import com.company.bookservice.service.BookService;
import jakarta.validation.constraints.NotEmpty;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/book")
@Validated
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/getAllBook")
    public ResponseEntity<List<BookDto>> getAllBook() {
        return ResponseEntity.ok(bookService.getAllBook());
    }

    @GetMapping("/isbn/{isbn}")
    public ResponseEntity<BookIdDto> getByIsbn(@PathVariable @NotEmpty String isbn) {
        return ResponseEntity.ok(bookService.getByIsbn(isbn));
    }

    @GetMapping("/book/{bookId}")
    public ResponseEntity<BookDto> getById(@PathVariable @NotEmpty String bookId){
        return ResponseEntity.ok(bookService.findBookDetailById(bookId));
    }

}
