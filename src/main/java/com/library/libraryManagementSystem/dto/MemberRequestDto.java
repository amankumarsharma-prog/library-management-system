package com.library.libraryManagementSystem.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class MemberRequestDto {

    @NotBlank(message = "NAME IS REQUIRED")
    @Size(min = 2,max = 30, message = " MIN NAME MUST BE 2 AND MAX 30 ")
    private String name;

    @NotBlank(message = "EMAIL ID REQUIRED")
    @Email(message = "ENTER A VALID EMAIL")
    private String email;

    @NotBlank(message = "PHONE NUMBER IS REQUIRED")
    @Pattern(regexp = "^[0-9]{10}$", message = "PHONE NUMBER MUST BE 10 DIGIT")
    private String phone;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
