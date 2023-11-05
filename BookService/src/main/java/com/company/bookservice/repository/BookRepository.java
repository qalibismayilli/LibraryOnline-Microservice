package com.company.bookservice.repository;

import com.company.bookservice.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

public interface BookRepository extends JpaRepository<Book, String> , JpaSpecificationExecutor<Book> {
    Optional<Book> getBookByIsbn(String isbn);
}
