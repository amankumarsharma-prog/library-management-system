package com.library.libraryManagementSystem.exception;

import com.library.libraryManagementSystem.dto.ExceptionResponseDto;
import com.library.libraryManagementSystem.dto.ValidationExceptionResponseDto;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler(DuplicateFoundException.class)
    private ResponseEntity<ExceptionResponseDto> duplicateFoundException(DuplicateFoundException ex, HttpServletRequest request){

        ExceptionResponseDto exceptionResponseDto = new ExceptionResponseDto(
                LocalDateTime.now(),
                HttpStatus.CONFLICT.value(),
                HttpStatus.CONFLICT.getReasonPhrase(),
                ex.getMessage(),
                request.getRequestURI()
        );
        return ResponseEntity.status(HttpStatus.CONFLICT).body(exceptionResponseDto);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    private ResponseEntity<ExceptionResponseDto> resourceNotFoundException(ResourceNotFoundException ex, HttpServletRequest request){

        ExceptionResponseDto exceptionResponseDto = new ExceptionResponseDto(
                LocalDateTime.now(),
                HttpStatus.NOT_FOUND.value(),
                HttpStatus.NOT_FOUND.getReasonPhrase(),
                ex.getMessage(),
                request.getRequestURI()
        );
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exceptionResponseDto);
    }
    @ExceptionHandler(RuntimeException.class)
    private ResponseEntity<ExceptionResponseDto> runTimeException(RuntimeException ex, HttpServletRequest request){

        ExceptionResponseDto runTimeExceptionHandler = new ExceptionResponseDto(
                LocalDateTime.now(),
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(),
                ex.getMessage(),
                request.getRequestURI()
        );
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(runTimeExceptionHandler);
    }


    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionResponseDto> globalExceptionHandler(Exception ex , HttpServletRequest request){

         ExceptionResponseDto generalExceptionHandler = new ExceptionResponseDto(
                 LocalDateTime.now(),
                 HttpStatus.INTERNAL_SERVER_ERROR.value(),
                 HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(),
                 ex.getMessage(),
                 request.getRequestURI()
         );
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(generalExceptionHandler);
    }


    @ExceptionHandler(IllegalStateException.class)
    private ResponseEntity<ExceptionResponseDto> illegalStateException(
            IllegalStateException ex,
            HttpServletRequest request) {

        ExceptionResponseDto response = new ExceptionResponseDto(
                LocalDateTime.now(),
                HttpStatus.CONFLICT.value(),
                HttpStatus.CONFLICT.getReasonPhrase(),
                ex.getMessage(),
                request.getRequestURI()
        );

        return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
    }


    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ValidationExceptionResponseDto> validationExceptionResponse(MethodArgumentNotValidException ex, HttpServletRequest request){

        Map<String, String> errorFields = new HashMap<>();

        ex.getBindingResult().getFieldErrors().forEach(error
                -> errorFields.put(error.getField(), error.getDefaultMessage()));

        ValidationExceptionResponseDto validationExceptionResponse = new ValidationExceptionResponseDto(
                LocalDateTime.now(),
                HttpStatus.BAD_REQUEST.value(),
                HttpStatus.BAD_REQUEST.getReasonPhrase(),
                "VALIDATION FAILED",
                request.getRequestURI(),
                errorFields
        );

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(validationExceptionResponse);
    }

}
