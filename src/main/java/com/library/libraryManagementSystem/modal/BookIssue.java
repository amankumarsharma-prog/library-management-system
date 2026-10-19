package com.library.libraryManagementSystem.modal;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "bookIssue_tb")
public class BookIssue {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(
            name = "book_id",
            nullable = false
    )
    private Books books;

    @ManyToOne
    @JoinColumn(
            name = "member_id",
            nullable = false
    )
    private Members members;

    @NotNull(message = "ISSUE DATE CANT BE NULL,EMPTY")
    private LocalDate issueDate;

    @NotNull(message = "DUE DATE CANT BE NULL,EMPTY")
    private LocalDate dueDate;
    private LocalDate returnDate;
    private LocalDateTime currentAt;
    private LocalDateTime updateAt;

    @Enumerated(EnumType.STRING)
    private IssueStatus status;

    private Double fine;

    public BookIssue(){

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Books getBooks() {
        return books;
    }

    public void setBooks(Books books) {
        this.books = books;
    }

    public Members getMembers() {
        return members;
    }

    public void setMembers(Members members) {
        this.members = members;
    }

    public LocalDate getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(LocalDate issueDate) {
        this.issueDate = issueDate;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
    }

    public LocalDateTime getCurrentAt() {
        return currentAt;
    }

    public void setCurrentAt(LocalDateTime currentAt) {
        this.currentAt = currentAt;
    }

    public LocalDateTime getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(LocalDateTime updateAt) {
        this.updateAt = updateAt;
    }

    public IssueStatus getStatus() {
        return status;
    }

    public void setStatus(IssueStatus status) {
        this.status = status;
    }

    public Double getFine() {
        return fine;
    }

    public void setFine(Double fine) {
        this.fine = fine;
    }
}
