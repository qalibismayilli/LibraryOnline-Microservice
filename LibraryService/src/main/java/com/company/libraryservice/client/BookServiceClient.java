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

//    Logger logger = LoggerFactory.getLogger(BookServiceClient.class);

    @GetMapping("/isbn/{isbn}")
//    @CircuitBreaker(name = "getByIsbnCircuitBreaker", fallbackMethod = "getBookFallback")
    ResponseEntity<BookIdDto> getByIsbn(@PathVariable(value = "isbn") @NotEmpty String isbn);

//    default ResponseEntity<BookIdDto> getBookFallback(String isbn, Exception exception) {
//        logger.info("Book not found by isbn:" + isbn + ", returning default BookIdDto object");
//        return ResponseEntity.ok(new BookIdDto("default-bookId", "default-isbn"));
//    }

    @GetMapping("/book/{bookId}")
//    @CircuitBreaker(name = "getById", fallbackMethod = "getBookByIdFallback")
    ResponseEntity<BookDto> getById(@PathVariable(value = "bookId") @NotEmpty String bookId);

//    default ResponseEntity<BookDto> getBookByIdFallback(String bookId, Exception exception){
//        logger.info("Book not found by id:" + bookId + ", returning default BookDto object");
//        return ResponseEntity.ok(new BookDto(new BookIdDto("default-bookId", "default-isbn")));
//    }
}
