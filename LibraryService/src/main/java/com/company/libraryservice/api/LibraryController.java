package com.company.libraryservice.api;

import com.company.libraryservice.dto.AddBookRequest;
import com.company.libraryservice.dto.LibraryDto;
import com.company.libraryservice.service.LibraryService;
import jakarta.validation.constraints.NotEmpty;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/library")
@Validated
public class LibraryController {

    private final LibraryService libraryService;

    public LibraryController(LibraryService libraryService) {
        this.libraryService = libraryService;
    }


    @PostMapping("/createLibrary")
    public ResponseEntity<LibraryDto> createLibrary(){
        return ResponseEntity.ok(libraryService.createLibrary());
    }

    @GetMapping("/library/{id}")
    public ResponseEntity getLibraryById(@PathVariable @NotEmpty String id){
        return ResponseEntity.ok(libraryService.getAllBooksInLibraryById(id));
    }

    @PutMapping("/addBookToLibrary")
    public ResponseEntity<Void> addBookToLibrary(@RequestBody AddBookRequest request){
        libraryService.addBookToLibrary(request);
        return ResponseEntity.ok().build();
    }
}
