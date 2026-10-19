package com.library.libraryManagementSystem.controller;

import com.library.libraryManagementSystem.service.UserService;
import com.library.libraryManagementSystem.dto.UserRequestDto;
import com.library.libraryManagementSystem.dto.UserResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<UserResponseDto> userRegister(@RequestBody UserRequestDto userRequest){
       UserResponseDto userResponse = userService.userRegister(userRequest);
       return ResponseEntity.status(HttpStatus.CREATED).body(userResponse);
    }
}
