package com.chaitu.fixmyride.controller;

import com.chaitu.fixmyride.dto.LoginRequest;
import com.chaitu.fixmyride.model.User;
import com.chaitu.fixmyride.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class UserController {
    @Autowired
    private UserService service;


    @GetMapping("/users")
    public ResponseEntity<List<User>> getUsers(
            @RequestParam(required = false) String username) {

        if (username != null) {
            List<User> users = Collections.singletonList(service.getUserByUsername(username));
            return new ResponseEntity<>(users, HttpStatus.OK);
        }

        return new ResponseEntity<>(service.getAllUsers(), HttpStatus.OK);
    }

    @GetMapping("/users/{username}")
    public ResponseEntity<User> getUserByUsername(@PathVariable String username) {

        User user = service.getUserByUsername(username);

        if(user != null){
            return new ResponseEntity<>(user, HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/user")
    public ResponseEntity<?> addUser(@RequestBody User user){
        try{
            User user1 = service.addUser(user);
            return new ResponseEntity<>(user1,HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/user/login")
    public ResponseEntity<String> login(
            @RequestBody LoginRequest loginRequest) {

        System.out.println("LOGIN API HIT");

        String token = service.verify(
                loginRequest.getUsername(),
                loginRequest.getPassword());

        if(token.equals("usernameNotFound")){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body("Username not found");
        }

        if(token.equals("passwordWrong")){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body("Incorrect password");
        }



        if(token.equals("failed")) {
            return new ResponseEntity<>(
                    "Invalid username or password",
                    HttpStatus.UNAUTHORIZED);
        }

        return ResponseEntity.ok(token);
    }




}
