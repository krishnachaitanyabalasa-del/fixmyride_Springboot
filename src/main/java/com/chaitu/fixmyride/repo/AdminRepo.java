package com.chaitu.fixmyride.repo;

import com.chaitu.fixmyride.model.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepo extends JpaRepository<Admin,String> {
    boolean existsByUsername(String username);

    Admin findByUsername(String username);

    Admin findByEmail(String email);
}