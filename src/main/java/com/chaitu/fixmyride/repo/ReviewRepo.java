package com.chaitu.fixmyride.repo;

import com.chaitu.fixmyride.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepo extends JpaRepository<Review, Integer> {

    List<Review> findByMechanicId(int mechanic_id);

    List<Review> findByUsername(int username);
}
