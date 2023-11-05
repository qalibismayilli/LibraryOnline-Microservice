package com.company.libraryservice.repository;

import com.company.libraryservice.model.Library;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LibraryRepository extends JpaRepository<Library, String> {
}
