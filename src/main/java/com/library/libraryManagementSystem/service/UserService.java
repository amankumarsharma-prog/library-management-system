package com.library.libraryManagementSystem.service;

import com.library.libraryManagementSystem.modal.Role;
import com.library.libraryManagementSystem.modal.Users;
import com.library.libraryManagementSystem.repository.RoleRepository;
import com.library.libraryManagementSystem.repository.UserRepository;
import com.library.libraryManagementSystem.dto.UserRequestDto;
import com.library.libraryManagementSystem.dto.UserResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;;

@Service
public class UserService {

    private RoleRepository roleRepository;
    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(RoleRepository roleRepository, UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public UserResponseDto userRegister(UserRequestDto userRequest){

        if(userRepository.existsByUsername(userRequest.getUsername())){
            throw new IllegalArgumentException("USER ALREADY EXIST");
        }

        Users users = new Users();
        users.setUsername(userRequest.getUsername());
        users.setPassword(passwordEncoder.encode(userRequest.getPassword()));

         Role role = roleRepository.findByName("ADMIN").orElseThrow(()-> new UsernameNotFoundException("ROLE NOT EXIST"));

        users.getRoles().add(role);

        Users saveUser = userRepository.save(users);

        return new UserResponseDto(
                saveUser.getUsername(),
                "REGISTRATION SUCCESSFULLY"
        );
    }
}
