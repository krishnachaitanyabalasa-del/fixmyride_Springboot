package com.chaitu.fixmyride.controller;

import com.chaitu.fixmyride.dto.LoginRequest;
import com.chaitu.fixmyride.model.User;
import com.chaitu.fixmyride.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
            List<User> users = service.getUserByUsername(username);
            return new ResponseEntity<>(users, HttpStatus.OK);
        }

        return new ResponseEntity<>(service.getAllUsers(), HttpStatus.OK);
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<User> getUserById(@PathVariable int id){
        User user = service.getUserById(id);
        if(user!=null){
            return new ResponseEntity<>(service.getUserById(id),HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
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
    public ResponseEntity<?> login(@RequestBody LoginRequest loginUser) {

        User dbUser = service.getUserByEmail(loginUser.getEmail());

        if (dbUser == null) {
            return new ResponseEntity<>("Invalid email or password",
                    HttpStatus.UNAUTHORIZED);
        }

        if (!dbUser.getPassword().equals(loginUser.getPassword())) {
            return new ResponseEntity<>("Invalid email or password",
                    HttpStatus.UNAUTHORIZED);
        }

        return new ResponseEntity<>(dbUser, HttpStatus.OK);
    }




}
