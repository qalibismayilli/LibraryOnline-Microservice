package com.company.libraryservice.api;

import com.company.libraryservice.dto.AddBookRequest;
import com.company.libraryservice.dto.LibraryDto;
import com.company.libraryservice.service.LibraryService;
import jakarta.validation.constraints.NotEmpty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/library")
@Validated
public class LibraryController {

    Logger logger = LoggerFactory.getLogger(LibraryController.class);

    private final Environment environment;

    private final LibraryService libraryService;

    public LibraryController(Environment environment, LibraryService libraryService) {
        this.environment = environment;
        this.libraryService = libraryService;
    }


    @PostMapping("/createLibrary")
    public ResponseEntity<LibraryDto> createLibrary() {
        logger.info("Library created on port number:" + environment.getProperty("local.server.port"));
        return ResponseEntity.ok(libraryService.createLibrary());
    }

    @GetMapping("/library/{id}")
    public ResponseEntity<LibraryDto> getLibraryById(@PathVariable @NotEmpty String id) {
        return ResponseEntity.ok(libraryService.getAllBooksInLibraryById(id));
    }

    @PutMapping("/addBookToLibrary")
    public ResponseEntity<Void> addBookToLibrary(@RequestBody AddBookRequest request) {
        libraryService.addBookToLibrary(request);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/getAllLibraries")
    public ResponseEntity<List<String>> getAllLibraries() {
        return ResponseEntity.ok(libraryService.getAllLibraries());
    }
}
