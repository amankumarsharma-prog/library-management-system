package com.library.libraryManagementSystem.exception;

public class DuplicateFoundException extends RuntimeException{
    public DuplicateFoundException(String message){
        super(message);
    }
}
