package com.chaitu.fixmyride.repo;

import com.chaitu.fixmyride.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<User,String> {
    boolean existsByUsername(String username);

    User findByEmail(String email);
    User findByUsername(String username);

}
