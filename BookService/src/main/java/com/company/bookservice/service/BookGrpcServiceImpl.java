package com.company.bookservice.service;

import com.company.bookservice.BookId;
import com.company.bookservice.BookServiceGrpc;
import com.company.bookservice.Isbn;
import com.company.bookservice.dto.BookIdDto;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@GrpcService
public class BookGrpcServiceImpl extends BookServiceGrpc.BookServiceImplBase {
    private static final Logger logger = LoggerFactory.getLogger(BookGrpcServiceImpl.class);

    private final BookService bookService;

    public BookGrpcServiceImpl( BookService bookService) {
        this.bookService = bookService;
    }

    @Override
    public void getBookIdByIsbn(Isbn request, StreamObserver<BookId> responseObserver) {
        logger.info("Grpc call received:" + request.getIsbn());

        BookIdDto bookIdDto = bookService.getByIsbn(request.getIsbn());

        responseObserver.onNext(
                BookId.newBuilder()
                        .setBookId(bookIdDto.getBookId())
                        .setIsbn(bookIdDto.getIsbn())
                        .build()
        );
        responseObserver.onCompleted();
    }
}
