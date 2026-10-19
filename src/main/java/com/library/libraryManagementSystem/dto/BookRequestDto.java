package com.library.libraryManagementSystem.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class BookRequestDto {

    @NotBlank(message = "TITLE IS REQUIRED")
    @Size(max = 30, message = " MAX TITLE MUST BE 30 ")
    private String title;

    @NotBlank(message = "AUTHOR IS REQUIRED")
    @Size(max = 30, message = " MAX AUTHOR MUST BE 30 ")
    private String author;

    @NotBlank(message = "ISBN IS REQUIRED")
    @Size(min = 10, message = " MIN ISBN MUST BE 10 ")
    private String isbn;
    private Integer quantity;
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
}
