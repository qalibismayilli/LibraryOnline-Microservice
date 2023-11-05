package com.company.libraryservice.service;

import com.company.libraryservice.dto.LibraryDto;
import com.company.libraryservice.exception.LibraryNotFoundException;
import com.company.libraryservice.model.Library;
import com.company.libraryservice.repository.LibraryRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LibraryService {
    private final LibraryRepository libraryRepository;

    public LibraryService(LibraryRepository libraryRepository) {
        this.libraryRepository = libraryRepository;
    }


    public LibraryDto getLibraryById(String id){
        Library library = libraryRepository.findById(id)
                .orElseThrow(() -> new LibraryNotFoundException("library could not found by id"));
        return new LibraryDto(library.getId());
    }

    @Transactional
    public LibraryDto createLibrary(){
        Library newLibrary = libraryRepository.save(new Library());
        return new LibraryDto(newLibrary.getId());
    }
}
