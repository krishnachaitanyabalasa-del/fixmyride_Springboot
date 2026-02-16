package com.chaitu.fixmyride.repo;

import com.chaitu.fixmyride.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepo extends JpaRepository<User,Integer> {
    User findByEmail(String email);
    List<User> findByUsername(String username);

}
