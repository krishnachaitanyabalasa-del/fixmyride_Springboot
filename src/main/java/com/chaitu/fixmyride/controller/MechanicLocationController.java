package com.chaitu.fixmyride.controller;

import com.chaitu.fixmyride.model.MechanicLocation;
import com.chaitu.fixmyride.service.MechanicLocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class MechanicLocationController {

    @Autowired
    private MechanicLocationService service;

    // 1️⃣ Get all mechanic locations
    @GetMapping("/mechanic-locations")
    public ResponseEntity<List<MechanicLocation>> getAllLocations() {
        return new ResponseEntity<>(service.getAllLocations(), HttpStatus.OK);
    }

    // 2️⃣ Get mechanic location by ID
    @GetMapping("/mechanic-locations/{mechanicId}")
    public ResponseEntity<MechanicLocation> getLocationById(@PathVariable int mechanicId) {
        MechanicLocation location = service.getByMechanicId(mechanicId);
        if (location != null) {
            return new ResponseEntity<>(location, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // 3️⃣ Add new mechanic location
    @PostMapping("/mechanic-location")
    public ResponseEntity<?> addLocation(@RequestBody MechanicLocation location) {
        try {
            MechanicLocation saved = service.addLocation(location);
            return new ResponseEntity<>(saved, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    // 4️⃣ Update mechanic location
    @PutMapping("/mechanic-location/{id}")
    public ResponseEntity<String> updateLocation(
            @PathVariable int id,
            @RequestBody MechanicLocation location) {

        MechanicLocation updated = service.updateLocation(id, location);

        if (updated != null) {
            return new ResponseEntity<>("updated", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Failed to update", HttpStatus.BAD_REQUEST);
        }
    }
    @PutMapping("/mechanic-locations/{mechanicId}")
    public ResponseEntity<?> updateByMechanicId(
            @PathVariable int mechanicId,
            @RequestBody MechanicLocation location) {

        MechanicLocation updated = service.updateByMechanicId(mechanicId, location);

        if (updated != null) {
            return new ResponseEntity<>("updated", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Not found", HttpStatus.NOT_FOUND);
        }
    }


    // 5️⃣ Delete mechanic location
    @DeleteMapping("/mechanic-location/{id}")
    public ResponseEntity<String> deleteLocationById(@PathVariable int id) {

        MechanicLocation location = service.getLocationById(id);

        if (location != null) {
            service.deleteLocationById(id);
            return new ResponseEntity<>("Deleted", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Location not found", HttpStatus.BAD_REQUEST);
        }
    }
}
