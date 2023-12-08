package com.company.libraryservice.client;


import com.company.libraryservice.dto.BookDto;
import com.company.libraryservice.dto.BookIdDto;
import jakarta.validation.constraints.NotEmpty;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "book-service", path = "/api/v1/book")
public interface BookServiceClient {

    @GetMapping("/isbn/{isbn}")
    ResponseEntity<BookIdDto> getByIsbn(@PathVariable @NotEmpty String isbn);

    @GetMapping("/book/{bookId}")
    ResponseEntity<BookDto> getById(@PathVariable @NotEmpty String bookId);
}
