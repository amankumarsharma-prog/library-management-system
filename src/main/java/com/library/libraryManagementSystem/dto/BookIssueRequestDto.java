package com.library.libraryManagementSystem.dto;

public class BookIssueRequestDto {

    private Long bookId;
    private Long memberId;

    public BookIssueRequestDto(){

    }

    public Long getBookId() {
        return bookId;
    }

    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }

    public Long getMemberId() {
        return memberId;
    }

    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }
}
