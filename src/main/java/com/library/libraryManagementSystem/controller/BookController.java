package com.library.libraryManagementSystem.controller;

import com.library.libraryManagementSystem.service.BookService;
import com.library.libraryManagementSystem.dto.BookRequestDto;
import com.library.libraryManagementSystem.dto.BookResponseDto;
import com.library.libraryManagementSystem.dto.UpdateBookResponseDto;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@RestController
@RequestMapping("/api/library")
public class BookController {

    private BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @PostMapping
    public ResponseEntity<BookResponseDto> createBook( @Valid @RequestBody BookRequestDto bookRequest) {
        BookResponseDto bookResponse = bookService.createBook(bookRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(bookResponse);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookResponseDto> getBook(@PathVariable Long id) {
        BookResponseDto bookResponse = bookService.getBook(id);
        return ResponseEntity.ok(bookResponse);
    }

    @GetMapping("/search")
    public ResponseEntity<Page> searchBooks(@RequestParam String title, Pageable pageable) {

        return ResponseEntity.ok(
                bookService.searchBooksByTitle(title, pageable));
    }


    @GetMapping
    public ResponseEntity<Page<BookResponseDto>> getAllBooks(Pageable pageable) {
        return ResponseEntity.ok(bookService.getAllBooks(pageable));
    }


    @PutMapping("{id}")
    public ResponseEntity<UpdateBookResponseDto> updateBook(@PathVariable Long id,@Valid @RequestBody BookRequestDto bookRequest) {
        UpdateBookResponseDto bookResponse = bookService.updateBook(id, bookRequest);
        return ResponseEntity.ok(bookResponse);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteBook(@PathVariable Long id) {
        bookService.deleteBook(id);
        return ResponseEntity.ok("Deleted");
    }
}
