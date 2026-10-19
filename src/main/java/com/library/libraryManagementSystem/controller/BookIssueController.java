package com.library.libraryManagementSystem.controller;

import com.library.libraryManagementSystem.service.BookIssueService;
import com.library.libraryManagementSystem.dto.BookIssueRequestDto;
import com.library.libraryManagementSystem.dto.BookIssueResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/bookIssue")
public class BookIssueController {

    private final BookIssueService bookIssueService;

    @Autowired
    public BookIssueController(BookIssueService bookIssueService){
        this.bookIssueService = bookIssueService;
    }

    @PostMapping
    public ResponseEntity<BookIssueResponseDto> issueBook(@RequestBody BookIssueRequestDto bookIssueRequest){
        BookIssueResponseDto bookIssueResponse = bookIssueService.issueBook(bookIssueRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(bookIssueResponse);
    }

    @PutMapping("/{issueId}")
    public ResponseEntity<BookIssueResponseDto>  returnBook(@PathVariable Long issueId){
        BookIssueResponseDto bookIssueResponse = bookIssueService.returnBook(issueId);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(bookIssueResponse);
    }
}
