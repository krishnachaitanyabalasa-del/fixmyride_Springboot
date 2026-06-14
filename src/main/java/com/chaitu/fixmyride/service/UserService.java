package com.chaitu.fixmyride.service;


import com.chaitu.fixmyride.dto.LoginRequest;
import com.chaitu.fixmyride.model.User;
import com.chaitu.fixmyride.repo.AdminRepo;
import com.chaitu.fixmyride.repo.MechanicRepo;
import com.chaitu.fixmyride.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class UserService {



    @Autowired
    private AuthenticationManager authManager;

    @Autowired
    private UserRepo repo;

    @Autowired
    private MechanicRepo mechanicRepo;



    @Autowired
    private AdminRepo adminRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    JWTService jwtService;



    public List<User> getAllUsers() {
        return repo.findAll();
    }

    public User getUserByUsername(String username) {
        return repo.findByUsername(username);
    }

    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
    public User addUser(User user) {

        if(repo.findByUsername(user.getUsername()) != null
                || mechanicRepo.findByUsername(user.getUsername()) != null
                || adminRepo.findByUsername(user.getUsername()) != null) {

            throw new RuntimeException("Username already exists");
        }

        user.setPassword(
                encoder.encode(user.getPassword())
        );
        return repo.save(user);
    }

    public User getUserByEmail(String email) {
        return repo.findByEmail(email);
    }

    public User searchUsersByUsername(String username) {
        return repo.findByUsername(username);
    }





    public String verify(String username, String password) {

        if (!repo.existsByUsername(username)) {
            return "usernameNotFound";
        }

        try {
            Authentication authentication =
                    authManager.authenticate(
                            new UsernamePasswordAuthenticationToken(
                                    username,
                                    password));

            if (authentication.isAuthenticated()) {
                return jwtService.generateToken(username);
            }

        } catch (BadCredentialsException e) {
            return "passwordWrong";
        }

        return "failed";
    }
}
