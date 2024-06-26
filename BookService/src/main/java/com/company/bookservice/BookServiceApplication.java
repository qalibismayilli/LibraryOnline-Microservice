package com.company.bookservice;

import com.company.bookservice.model.Book;
import com.company.bookservice.repository.BookRepository;
import io.grpc.netty.shaded.io.grpc.netty.NettyServerBuilder;
import net.devh.boot.grpc.server.serverfactory.GrpcServerConfigurer;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;

import java.util.List;
import java.util.concurrent.TimeUnit;

@SpringBootApplication
@EnableDiscoveryClient
public class BookServiceApplication implements CommandLineRunner {
    private final BookRepository bookRepository;

    public BookServiceApplication(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public static void main(String[] args) {
        SpringApplication.run(BookServiceApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        Book book1 = new Book("Yeniden bashlamaq mumkun olsaydi",
                1987, "Mark Levy",
                "Pegasus", "213123");

        Book book2 = new Book("The Midnight Library",
                2013, "Matt Haig",
                "Domingo", "481149283");

        Book book3 = new Book("Tanri denklemi",
                2002, "Michio Kaku",
                "ODTU Yayincilik", "41231324431");

        Book book4 = new Book("OyunBaz",
                2002, "Wulf Dorn",
                "Pegasus", "73437132784");

        Book book5 = new Book("Nutuk",
                1965,"Mustafa Kemal Ataturk"
                , "ODTU Yayincilik","234234623");

        List<Book> list = bookRepository.saveAll(List.of(book1, book2, book3, book4, book5));
        System.out.println(list);
    }

    @Bean
    public GrpcServerConfigurer keepAliveServerConfigurer() {
        return serverBuilder -> {
            if (serverBuilder instanceof NettyServerBuilder) {
                ((NettyServerBuilder) serverBuilder).keepAliveTime(30, TimeUnit.SECONDS)
                        .keepAliveTimeout(5, TimeUnit.SECONDS).permitKeepAliveWithoutCalls(true);
            }
        };
    }
}
