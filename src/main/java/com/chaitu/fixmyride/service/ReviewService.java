package com.chaitu.fixmyride.service;

import com.chaitu.fixmyride.model.Review;
import com.chaitu.fixmyride.repo.ReviewRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ReviewService {

    @Autowired
    private ReviewRepo repo;

    // Get all reviews
    public List<Review> getAllReviews() {
        return repo.findAll();
    }

    // Get review by ID
    public Review getReviewById(int id) {
        return repo.findById(id).orElse(null);
    }

    // Get reviews for a mechanic
    public List<Review> getReviewsByMechanicId(int mechanicId) {
        return repo.findByMechanicId(mechanicId);
    }

    // Get reviews by user
    public List<Review> getReviewsByUsername(int username) {
        return repo.findByUsername(username);
    }

    // Add review
    public Review addReview(Review review) {
        review.setCreated_at(new Date());
        return repo.save(review);
    }

    // Delete review
    public void deleteReviewById(int id) {
        repo.deleteById(id);
    }
}
