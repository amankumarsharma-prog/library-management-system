package com.library.libraryManagementSystem.repository;

import com.library.libraryManagementSystem.modal.Books;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Books,Long> {

    Boolean existsByIsbn(String isbn);

    Page<Books> findByTitleContainingIgnoreCase(String title, Pageable pageable);
}
