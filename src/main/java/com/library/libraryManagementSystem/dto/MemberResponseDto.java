package com.library.libraryManagementSystem.dto;

import java.time.LocalDateTime;

public class MemberResponseDto {

    private Long id;
    private String name;
    private String email;
    private String phone;
    private LocalDateTime currentAt;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public LocalDateTime getCurrentAt() {
        return currentAt;
    }

    public void setCurrentAt(LocalDateTime currentAt) {
        this.currentAt = currentAt;
    }
}
