package com.library.libraryManagementSystem.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class LoginRequestDto {

    @NotBlank(message = "USERNAME IS REQUIRED")
    @Size(min = 3, max = 40)
    private String username;

    @NotBlank(message = "USERNAME IS REQUIRED")
    @Size(min = 3, max = 40)
    private String password;
}
