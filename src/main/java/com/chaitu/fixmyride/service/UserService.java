package com.chaitu.fixmyride.service;

import com.chaitu.fixmyride.model.User;
import com.chaitu.fixmyride.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepo repo;

    public List<User> getAllUsers(){
        return repo.findAll();
    }

    public User getUserById(int id) {
        return repo.findById(id).orElse(null);
    }


    public User addUser(User user) {
        return repo.save(user);
    }

    public User getUserByEmail(String email) {
        return repo.findByEmail(email);
    }
    public List<User> getUserByUsername(String username) {
        return repo.findByUsername(username);
    }

}
