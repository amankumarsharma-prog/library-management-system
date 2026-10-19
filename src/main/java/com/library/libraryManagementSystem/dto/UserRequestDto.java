package com.library.libraryManagementSystem.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class UserRequestDto {

    @NotBlank(message = "USERNAME IS REQUIRED")
    @Size(min = 3, max = 50)
    private String username;

    @NotBlank(message = "PASSWORD IS REQUIRED")
    @Size(min = 3, max = 50)
    private String password;

    public UserRequestDto(){

    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
