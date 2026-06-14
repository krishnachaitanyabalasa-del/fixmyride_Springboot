package com.chaitu.fixmyride.controller;

import com.chaitu.fixmyride.dto.LoginRequest;
import com.chaitu.fixmyride.model.Mechanic;
import com.chaitu.fixmyride.model.User;
import com.chaitu.fixmyride.service.MechanicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class MechanicController {

    @Autowired
    private MechanicService service;

    @GetMapping("/mechanics")
    public ResponseEntity<List<Mechanic>> getApprovedMechanics() {

        List<Mechanic> mechanics =
                service.getApprovedMechanics();

        return new ResponseEntity<>(
                mechanics,
                HttpStatus.OK
        );
    }

    @GetMapping("/all-mechanics")
    public ResponseEntity<List<Mechanic>> getMechanics(
            @RequestParam(required = false) String username) {

        if (username != null) {
            List<Mechanic> mechanics = Collections.singletonList(service.getMechanicByUsername(username));
            return new ResponseEntity<>(mechanics, HttpStatus.OK);
        }

        return new ResponseEntity<>(service.getAllMechanics(), HttpStatus.OK);
    }

    @GetMapping("/mechanics/{username}")
    public ResponseEntity<Mechanic> getMechanicByUsername(@PathVariable String username){
        Mechanic mechanic = service.getMechanicByUsername(username);
        if(mechanic!=null){
            return new ResponseEntity<>(service.getMechanicByUsername(username),HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/mechanics/active")
    public ResponseEntity<List<Mechanic>> getActiveMechanics() {
        return ResponseEntity.ok(
                service.getActiveMechanics()
        );
    }

    @GetMapping("/mechanics/pending")
    public ResponseEntity<List<Mechanic>> getPendingMechanics() {
        return ResponseEntity.ok(
                service.getPendingMechanics()
        );
    }

    @PostMapping("/mechanic")
    public ResponseEntity<?> addMechanic(@RequestBody Mechanic mechanic){
        try{
            Mechanic mechanic1 = service.addMechanic(mechanic);
            return new ResponseEntity<>(mechanic1,HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/mechanic/login")
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



        if(token.equals("pending")){
            return new ResponseEntity<>(
                    "Your account is awaiting admin approval.",
                    HttpStatus.UNAUTHORIZED);
        }
        if(token.equals("rejected")){
            return new ResponseEntity<>(
                    "Your account registration was rejected.",
                    HttpStatus.UNAUTHORIZED);
        }


        return ResponseEntity.ok(token);

    }

    @PutMapping("/mechanic/{username}")
    public ResponseEntity<?> updateMechanic(
            @PathVariable String username,
            @RequestBody Mechanic mechanic) {

        try {
            Mechanic updatedMechanic =
                    service.updateMechanic(username, mechanic);

            return new ResponseEntity<>(
                    updatedMechanic,
                    HttpStatus.OK);

        } catch (Exception e) {

            return new ResponseEntity<>(
                    e.getMessage(),
                    HttpStatus.BAD_REQUEST);
        }
    }


}
