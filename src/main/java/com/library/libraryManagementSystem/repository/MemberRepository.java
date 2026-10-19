package com.library.libraryManagementSystem.repository;

import com.library.libraryManagementSystem.modal.Members;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Members, Long>{

    Boolean existsByEmail(String email);
}
