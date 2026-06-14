package com.chaitu.fixmyride.controller;

import com.chaitu.fixmyride.dto.LoginRequest;
import com.chaitu.fixmyride.model.Admin;
import com.chaitu.fixmyride.model.Mechanic;
import com.chaitu.fixmyride.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/admin")
public class AdminController {

    @Autowired
    private AdminService service;

    @PostMapping
    public ResponseEntity<?> addAdmin(
            @RequestBody Admin admin){

        try{
            Admin admin1 =
                    service.addAdmin(admin);

            return new ResponseEntity<>(
                    admin1,
                    HttpStatus.OK);

        }catch(Exception e){

            return new ResponseEntity<>(
                    e.getMessage(),
                    HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(
            @RequestBody LoginRequest loginRequest){
        System.out.println("Admin login api hit");
        String token =
                service.verify(
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


    @PutMapping("/approve/{username}")
    public ResponseEntity<String> approveMechanic(
            @PathVariable String username) {

        String response = service.approveMechanic(username);

        if(response.equals("Mechanic not found")) {
            return new ResponseEntity<>(
                    response,
                    HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(
                response,
                HttpStatus.OK);
    }

    @PutMapping("/reject/{username}")
    public ResponseEntity<String> rejectMechanic(
            @PathVariable String username) {

        String response = service.rejectMechanic(username);

        if(response.equals("Mechanic not found")) {
            return new ResponseEntity<>(
                    response,
                    HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(
                response,
                HttpStatus.OK);
    }

    @GetMapping("/pending-mechanics")
    public ResponseEntity<List<Mechanic>> getPendingMechanics() {

        List<Mechanic> mechanics =
                service.getPendingMechanics();

        return new ResponseEntity<>(
                mechanics,
                HttpStatus.OK);
    }

    @GetMapping("/{username}")
    public ResponseEntity<Admin> getAdminByUsername(
            @PathVariable String username) {

        Admin admin = service.getAdminByUsername(username);

        if (admin != null) {
            return new ResponseEntity<>(
                    admin,
                    HttpStatus.OK);
        }

        return new ResponseEntity<>(
                HttpStatus.NOT_FOUND);
    }
}