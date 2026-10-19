package com.library.libraryManagementSystem.service;

import com.library.libraryManagementSystem.modal.CustomUserDetails;
import com.library.libraryManagementSystem.modal.Users;
import com.library.libraryManagementSystem.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private UserRepository userRepository;

    @Autowired
    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
       Users users = userRepository.findByUsername(username).orElseThrow(()-> new UsernameNotFoundException("USERNAME NOT FOUND"));

       return new CustomUserDetails(users);
    }
}
