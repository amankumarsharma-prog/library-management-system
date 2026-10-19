package com.library.libraryManagementSystem.service;

import com.library.libraryManagementSystem.exception.ResourceNotFoundException;
import com.library.libraryManagementSystem.modal.BookIssue;
import com.library.libraryManagementSystem.modal.Books;
import com.library.libraryManagementSystem.modal.IssueStatus;
import com.library.libraryManagementSystem.modal.Members;
import com.library.libraryManagementSystem.repository.BookIssueRepository;
import com.library.libraryManagementSystem.repository.BookRepository;
import com.library.libraryManagementSystem.repository.MemberRepository;
import com.library.libraryManagementSystem.dto.BookIssueRequestDto;
import com.library.libraryManagementSystem.dto.BookIssueResponseDto;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;


@Service
public class BookIssueService {

    private final BookIssueRepository bookIssueRepository;
    private final BookRepository bookRepository;
    private final MemberRepository memberRepository;

    @Autowired
    public BookIssueService(BookIssueRepository bookIssueRepository, BookRepository bookRepository, MemberRepository memberRepository) {
        this.bookIssueRepository = bookIssueRepository;
        this.bookRepository = bookRepository;
        this.memberRepository = memberRepository;
    }

    @Transactional
    public BookIssueResponseDto issueBook(BookIssueRequestDto bookIssueRequest) {

        //FIND BOOK
        Books availableBook = bookRepository.findById(bookIssueRequest.getBookId())
                .orElseThrow(() -> new ResourceNotFoundException("BOOK NOT FOUND"));

        // FIND MEMBER
        Members availableMember = memberRepository.findById(bookIssueRequest.getMemberId())
                .orElseThrow(() -> new ResourceNotFoundException("MEMBER NOT FOUND"));

        //CHECK BOOK QUANTITY
        if(availableBook.getQuantity() == null || availableBook.getQuantity() <= 0){
            throw new RuntimeException("BOOK IS NOT AVAILABLE.");
        }

        //CREATE BOOK ISSUE
        BookIssue bookIssue = new BookIssue();

        bookIssue.setBooks(availableBook);
        bookIssue.setMembers(availableMember);
        bookIssue.setIssueDate(LocalDate.now());
        bookIssue.setDueDate(LocalDate.now().plusDays(7));
        bookIssue.setStatus(IssueStatus.ISSUED);

        //UPDATE BOOK
        availableBook.setQuantity(availableBook.getQuantity() - 1);

        //SAVE ISSUE RECORD
        bookRepository.save(availableBook);

         BookIssue savedIssue = bookIssueRepository.save(bookIssue);

         return mapToDto(savedIssue);
    }

    @Transactional
    public BookIssueResponseDto returnBook(Long issueId){

        //FIND ISSUE ID
        BookIssue existIssueId = bookIssueRepository.findById(issueId)
                .orElseThrow(()-> new ResourceNotFoundException("ISSUE ID NOT FOUND.."));

        //CHECK IF BOOK ID RETURN OR NOT
        if(existIssueId.getStatus() == IssueStatus.RETURNED){
            throw new RuntimeException("BOOK IS ALREADY RETURN.");
        }

        //UPDATE BOOK
        Books saveBook = existIssueId.getBooks();
        saveBook.setQuantity(saveBook.getQuantity() + 1);
        bookRepository.save(saveBook);

        //STATUS UPDATE
        existIssueId.setStatus(IssueStatus.RETURNED);
        existIssueId.setReturnDate(LocalDate.now());

        // CALCULATE FINE
        LocalDate dueDate = existIssueId.getDueDate();
        LocalDate returnDate = LocalDate.now();

        double fine = calculateFine(dueDate,returnDate);
        existIssueId.setFine(fine);
        BookIssue saveResponse = bookIssueRepository.save(existIssueId);
        return mapToDto(saveResponse);
    }

    public BookIssueResponseDto mapToDto(BookIssue savedIssue){

        BookIssueResponseDto bookIssueResponse = new BookIssueResponseDto();

        bookIssueResponse.setBookId(savedIssue.getBooks().getId());
        bookIssueResponse.setId(savedIssue.getId());
        bookIssueResponse.setMemberId(savedIssue.getMembers().getId());
        bookIssueResponse.setDueDate(savedIssue.getDueDate());
        bookIssueResponse.setIssueDate(savedIssue.getIssueDate());
        bookIssueResponse.setReturnDate(savedIssue.getReturnDate());
        bookIssueResponse.setStatus(savedIssue.getStatus());
        bookIssueResponse.setFine(savedIssue.getFine());

        return bookIssueResponse;
    }

    private double  calculateFine(LocalDate dueDate, LocalDate returnDate){

        long lateDays = ChronoUnit.DAYS.between(dueDate,returnDate);

        if(lateDays <= 0){
            return 0.0;
        }
        double perDayFine = 50.0;
        return lateDays * perDayFine;
    }
}
