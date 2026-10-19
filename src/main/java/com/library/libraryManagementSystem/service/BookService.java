package com.library.libraryManagementSystem.service;

import com.library.libraryManagementSystem.modal.Books;
import com.library.libraryManagementSystem.exception.DuplicateFoundException;
import com.library.libraryManagementSystem.exception.ResourceNotFoundException;
import com.library.libraryManagementSystem.repository.BookIssueRepository;
import com.library.libraryManagementSystem.repository.BookRepository;
import com.library.libraryManagementSystem.dto.BookRequestDto;
import com.library.libraryManagementSystem.dto.BookResponseDto;
import com.library.libraryManagementSystem.dto.UpdateBookResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.time.LocalDateTime;

@Service
public class BookService {

    private BookRepository bookRepository;
    private final BookIssueRepository bookIssueRepository;
    @Autowired
    public BookService(BookRepository bookRepository, BookIssueRepository bookIssueRepository) {
        this.bookRepository = bookRepository;
        this.bookIssueRepository = bookIssueRepository;
    }

    public BookResponseDto createBook(BookRequestDto bookRequest){

        Books bookToSave = mapToEntity(bookRequest);
        if(existIsbn(bookToSave)){
            throw new DuplicateFoundException("ISBN " + bookToSave.getIsbn() + " IS ALREADY EXIST.");
        }
        Books bookResponse = bookRepository.save(bookToSave);
        return mapToDto(bookResponse);
    }

    public BookResponseDto getBook(Long id){
        Books bookResponse = bookRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Id " + id + " Not Found"));
        return mapToDto(bookResponse);
    }

    public Page<BookResponseDto> getAllBooks(Pageable pageable) {
        Page<Books> booksPage = bookRepository.findAll(pageable);
        return booksPage.map(this::mapToDto);
    }

    public Page<BookResponseDto> searchBooksByTitle(String title, Pageable pageable) {
        Page<Books> booksPage = bookRepository.findByTitleContainingIgnoreCase(title, pageable);

        return booksPage.map(this::mapToDto);
    }
    public void deleteBook(Long id) {

        Books book = bookRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Book Id " + id + " Not Found"
                        ));

        boolean hasIssueRecords =
                bookIssueRepository.existsByBooks_Id(id);

        if (hasIssueRecords) {
            throw new IllegalStateException(
                    "Cannot delete this book because issue records exist."
            );
        }

        bookRepository.delete(book);
    }

    public UpdateBookResponseDto updateBook(Long id, BookRequestDto bookRequest){
        Books bookToSave = bookRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Id " + id + " Not Found"));

        bookToSave.setAuthor(bookRequest.getAuthor());
        bookToSave.setTitle(bookRequest.getTitle());
        bookToSave.setIsbn(bookRequest.getIsbn());
        bookToSave.setQuantity(bookRequest.getQuantity());
        bookToSave.setUpdateAt(LocalDateTime.now());

       Books bookResponse = bookRepository.save(bookToSave);

       return mapToUpdateDto(bookResponse);

    }

    private Books mapToEntity(BookRequestDto bookRequest){

        Books bookToSave = new Books();

        bookToSave.setAuthor(bookRequest.getAuthor());
        bookToSave.setTitle(bookRequest.getTitle());
        bookToSave.setIsbn(bookRequest.getIsbn());
        bookToSave.setQuantity(bookRequest.getQuantity());
        bookToSave.setCurrentAt(LocalDateTime.now());
        bookToSave.setUpdateAt(LocalDateTime.now());

        return bookToSave;
    }

   private BookResponseDto mapToDto(Books bookEntity) {
       BookResponseDto convertodDto = new BookResponseDto();

       convertodDto.setId(bookEntity.getId());
       convertodDto.setAuthor(bookEntity.getAuthor());
       convertodDto.setTitle(bookEntity.getTitle());
       convertodDto.setIsbn(bookEntity.getIsbn());
       convertodDto.setQuantity(bookEntity.getQuantity());
       convertodDto.setCurrentAt(bookEntity.getCurrentAt());

       return convertodDto;
   }

   private UpdateBookResponseDto mapToUpdateDto(Books bookResponse){
        UpdateBookResponseDto updateBookResponseDto = new UpdateBookResponseDto();
        updateBookResponseDto.setId(bookResponse.getId());
        updateBookResponseDto.setTitle(bookResponse.getTitle());
        updateBookResponseDto.setAuthor(bookResponse.getAuthor());
        updateBookResponseDto.setIsbn(bookResponse.getIsbn());
        updateBookResponseDto.setQuantity(bookResponse.getQuantity());
        updateBookResponseDto.setUpdateAt(bookResponse.getUpdateAt());

        return updateBookResponseDto;
   }

   public Boolean existIsbn(Books isbnCheck){
       return bookRepository.existsByIsbn(isbnCheck.getIsbn());
   }
}