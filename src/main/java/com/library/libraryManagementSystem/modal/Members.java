package com.library.libraryManagementSystem.modal;

import jakarta.persistence.*;

import java.lang.reflect.Member;
import java.time.LocalDateTime;

@Entity
public class Members {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @Column(
            unique = true,
            nullable = false
    )
    private String email;
    private String phone;
    private LocalDateTime currentAt;
    private LocalDateTime updateAt;

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

    public LocalDateTime getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(LocalDateTime updateAt) {
        this.updateAt = updateAt;
    }
}
