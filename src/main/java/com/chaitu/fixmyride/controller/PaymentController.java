package com.chaitu.fixmyride.controller;

import com.chaitu.fixmyride.model.Payment;
import com.chaitu.fixmyride.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class PaymentController {

    @Autowired
    private PaymentService service;

    @GetMapping("/payments")
    public ResponseEntity<List<Payment>> getAllPayments() {
        return new ResponseEntity<>(service.getAllPayments(), HttpStatus.OK);
    }

    @GetMapping("/payments/{id}")
    public ResponseEntity<Payment> getPaymentById(@PathVariable int id) {
        Payment payment = service.getPaymentById(id);
        if (payment != null) {
            return new ResponseEntity<>(payment, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/payment")
    public ResponseEntity<?> addPayment(@RequestBody Payment payment) {
        try {
            Payment saved = service.addPayment(payment);
            return new ResponseEntity<>(saved, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/payment/{id}")
    public ResponseEntity<String> deletePayment(@PathVariable int id) {
        Payment payment = service.getPaymentById(id);
        if (payment != null) {
            service.deletePaymentById(id);
            return new ResponseEntity<>("Deleted", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Payment not found", HttpStatus.BAD_REQUEST);
        }
    }
}
