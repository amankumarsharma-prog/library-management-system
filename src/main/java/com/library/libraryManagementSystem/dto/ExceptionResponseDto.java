package com.library.libraryManagementSystem.dto;

import java.time.LocalDateTime;

public class ExceptionResponseDto {

    private LocalDateTime timestamp;
    private int stausCode;
    private String error;
    private String message;
    private String path;


    public ExceptionResponseDto(LocalDateTime timestamp, int stausCode, String error, String message, String path) {
        this.timestamp = timestamp;
        this.stausCode = stausCode;
        this.error = error;
        this.message = message;
        this.path = path;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public int getStausCode() {
        return stausCode;
    }

    public void setStausCode(int stausCode) {
        this.stausCode = stausCode;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
