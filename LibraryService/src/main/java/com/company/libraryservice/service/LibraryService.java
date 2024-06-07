package com.company.libraryservice.service;

import com.company.bookservice.BookId;
import com.company.bookservice.BookServiceGrpc;
import com.company.bookservice.Isbn;
import com.company.libraryservice.client.BookServiceClient;
import com.company.libraryservice.dto.AddBookRequest;
import com.company.libraryservice.dto.LibraryDto;
import com.company.libraryservice.exception.LibraryNotFoundException;
import com.company.libraryservice.model.Library;
import com.company.libraryservice.repository.LibraryRepository;
import jakarta.transaction.Transactional;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LibraryService {
    private final LibraryRepository libraryRepository;
    private final BookServiceClient bookServiceClient;

    @GrpcClient("bookService")
    private BookServiceGrpc.BookServiceBlockingStub bookServiceBlockingStub;

    public LibraryService(LibraryRepository libraryRepository, BookServiceClient bookServiceClient) {
        this.libraryRepository = libraryRepository;
        this.bookServiceClient = bookServiceClient;
    }

    public LibraryDto getAllBooksInLibraryById(String id) {
        Library library = libraryRepository.findById(id)
                .orElseThrow(() -> new LibraryNotFoundException("library could not found by id"));

        return new LibraryDto(library.getId(),
                library.getUserBook()
                        .stream()
                        .map(bookId -> bookServiceClient.getById(bookId).getBody())
                        .toList());
    }

    @Transactional
    public LibraryDto createLibrary() {
        Library newLibrary = libraryRepository.save(new Library());
        return new LibraryDto(newLibrary.getId());
    }

    @Transactional
    public void addBookToLibrary(AddBookRequest request) {

//        String bookId = bookServiceClient.getByIsbn(request.getIsbn()).getBody().getBookId();

        BookId bookIdByIsbn = bookServiceBlockingStub
                .getBookIdByIsbn(Isbn.newBuilder().setIsbn(request.getIsbn()).build());

        String bookId = bookIdByIsbn.getBookId();

        Library library = libraryRepository.findById(request.getId())
                .orElseThrow(() -> new LibraryNotFoundException("library could not found by id"));

        library.getUserBook().add(bookId);

        libraryRepository.save(library);
    }

    public List<String> getAllLibraries() {
        return libraryRepository.findAll()
                .stream()
                .map(l -> l.getId())
                .toList();
    }
}