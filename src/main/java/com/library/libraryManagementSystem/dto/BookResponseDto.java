package com.library.libraryManagementSystem.dto;

import java.time.LocalDateTime;

public class BookResponseDto {

    private Long id;
    private String title;
    private String author;
    private String isbn;
    private Integer quantity;
    private LocalDateTime currentAt;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public LocalDateTime getCurrentAt() {
        return currentAt;
    }

    public void setCurrentAt(LocalDateTime currentAt) {
        this.currentAt = currentAt;
    }
}
