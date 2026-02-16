package com.chaitu.fixmyride.controller;

import com.chaitu.fixmyride.model.ServiceRequest;
import com.chaitu.fixmyride.service.ServiceRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class ServiceRequestController {

    @Autowired
    private ServiceRequestService service;

    // 1️⃣ Get all service requests
    @GetMapping("/requests")
    public ResponseEntity<List<ServiceRequest>> getAllRequests() {
        return new ResponseEntity<>(service.getAllRequests(), HttpStatus.OK);
    }

    // 2️⃣ Get service request by id
    @GetMapping("/requests/{id}")
    public ResponseEntity<ServiceRequest> getRequestById(@PathVariable int id) {
        ServiceRequest request = service.getRequestById(id);
        if (request != null) {
            return new ResponseEntity<>(request, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // 3️⃣ Create a new service request
    @PostMapping("/request")
    public ResponseEntity<?> addRequest(@RequestBody ServiceRequest request) {
        System.out.println("REQUEST RECEIVED >>> " + request);
        try {
            ServiceRequest savedRequest = service.addRequest(request);
            return new ResponseEntity<>(savedRequest, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/request/{id}")
    public ResponseEntity<String> updateRequest(
            @PathVariable int id,
            @RequestBody ServiceRequest request) {

        ServiceRequest updatedRequest = service.updateRequest(id, request);

        if (updatedRequest != null) {
            return new ResponseEntity<>("updated", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Failed to update", HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/request/{id}")
    public ResponseEntity<String> deleteRequestById(@PathVariable int id){
        ServiceRequest request1 = service.getRequestById(id);
        if(request1!=null){
            service.deleteRequestById(id);
            return new ResponseEntity<>("Deleted",HttpStatus.OK);
        }else{
            return new ResponseEntity<>("Request not found",HttpStatus.BAD_REQUEST);
        }
    }




}
