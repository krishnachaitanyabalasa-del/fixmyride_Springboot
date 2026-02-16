package com.chaitu.fixmyride.controller;

import com.chaitu.fixmyride.dto.LoginRequest;
import com.chaitu.fixmyride.model.Mechanic;
import com.chaitu.fixmyride.model.User;
import com.chaitu.fixmyride.service.MechanicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class MechanicController {

    @Autowired
    private MechanicService service;

    @GetMapping("/mechanics")
    public ResponseEntity<List<Mechanic>> getAllMechanics(){
        return new ResponseEntity<>(service.getAllMechanics(), HttpStatus.OK);
    }

    @GetMapping("/mechanics/{id}")
    public ResponseEntity<Mechanic> getMechanicById(@PathVariable int id){
        Mechanic mechanic = service.getMechanicById(id);
        if(mechanic!=null){
            return new ResponseEntity<>(service.getMechanicById(id),HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
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
    public ResponseEntity<?> login(@RequestBody LoginRequest loginMechanic) {

        Mechanic dbMechanic = service.getMechanicByEmail(loginMechanic.getEmail());
        System.out.println("FOUND MECHANIC = " + dbMechanic);


        if (dbMechanic == null) {
            return new ResponseEntity<>("Invalid email or password",
                    HttpStatus.UNAUTHORIZED);
        }

        if (dbMechanic.getPassword() == null ||
                !dbMechanic.getPassword().equals(loginMechanic.getPassword())) {
            return new ResponseEntity<>("Invalid email or password",
                    HttpStatus.UNAUTHORIZED);
        }

        return new ResponseEntity<>(dbMechanic, HttpStatus.OK);
    }


}
