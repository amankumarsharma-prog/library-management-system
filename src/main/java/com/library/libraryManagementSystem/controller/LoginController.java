package com.library.libraryManagementSystem.controller;

import com.library.libraryManagementSystem.service.JwtService;
import com.library.libraryManagementSystem.dto.LoginRequestDto;
import com.library.libraryManagementSystem.dto.LoginResponseDto;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth/login")
public class LoginController {

    private AuthenticationManager authenticationManager;
    private JwtService jwtService;

    @Autowired
    public LoginController(AuthenticationManager authenticationManager,JwtService jwtService) {
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
    }

    @PostMapping
    public LoginResponseDto login( @Valid @RequestBody  LoginRequestDto loginRequest){

        Authentication authentication = UsernamePasswordAuthenticationToken.unauthenticated(
                loginRequest.getUsername(),
                loginRequest.getPassword()
        );

        Authentication authentication1 = authenticationManager.authenticate(authentication);

        String token = jwtService.generateToken(authentication1);

        return new LoginResponseDto(token);
    }
}
