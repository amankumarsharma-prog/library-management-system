package com.library.libraryManagementSystem.repository;

import com.library.libraryManagementSystem.modal.Users;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<Users,Long> {

    @EntityGraph(attributePaths = "roles")
    Optional<Users> findByUsername(String username);

    Boolean existsByUsername(String username);
}
