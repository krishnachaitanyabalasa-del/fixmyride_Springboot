package com.chaitu.fixmyride.controller;

import com.chaitu.fixmyride.model.Review;
import com.chaitu.fixmyride.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class ReviewController {

    @Autowired
    private ReviewService service;

    // 1️⃣ Get all reviews
    @GetMapping("/reviews")
    public ResponseEntity<List<Review>> getAllReviews() {
        return new ResponseEntity<>(service.getAllReviews(), HttpStatus.OK);
    }

    // 2️⃣ Get review by ID
    @GetMapping("/reviews/{id}")
    public ResponseEntity<Review> getReviewById(@PathVariable int id) {
        Review review = service.getReviewById(id);
        if (review != null) {
            return new ResponseEntity<>(review, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // 3️⃣ Get reviews by mechanic
    @GetMapping("/reviews/mechanic/{mechanicId}")
    public ResponseEntity<List<Review>> getReviewsByMechanicId(
            @PathVariable int mechanicId) {

        return new ResponseEntity<>(
                service.getReviewsByMechanicId(mechanicId),
                HttpStatus.OK
        );
    }

    // 4️⃣ Get reviews by user
    @GetMapping("/reviews/user/{username}")
    public ResponseEntity<List<Review>> getReviewsByUsername(
            @PathVariable int username) {

        return new ResponseEntity<>(
                service.getReviewsByUsername(username),
                HttpStatus.OK
        );
    }

    // 5️⃣ Add review
    @PostMapping("/review")
    public ResponseEntity<?> addReview(@RequestBody Review review) {
        try {
            Review saved = service.addReview(review);
            return new ResponseEntity<>(saved, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    // 6️⃣ Delete review
    @DeleteMapping("/review/{id}")
    public ResponseEntity<String> deleteReview(@PathVariable int id) {
        Review review = service.getReviewById(id);
        if (review != null) {
            service.deleteReviewById(id);
            return new ResponseEntity<>("Deleted", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Review not found", HttpStatus.BAD_REQUEST);
        }
    }
}
