package com.library.libraryManagementSystem.repository;

import com.library.libraryManagementSystem.modal.BookIssue;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookIssueRepository extends JpaRepository<BookIssue, Long> {
    boolean existsByBooks_Id(Long bookId);
}
