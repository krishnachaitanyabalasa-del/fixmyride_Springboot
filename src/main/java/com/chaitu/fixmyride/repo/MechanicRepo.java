package com.chaitu.fixmyride.repo;

import com.chaitu.fixmyride.model.Mechanic;
import com.chaitu.fixmyride.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MechanicRepo extends JpaRepository<Mechanic,Integer> {
    boolean existsByUsername(String username);
    Mechanic findByEmail(String email);
    Mechanic findByUsername(String username);
    List<Mechanic> findByStatus(String status);
    List<Mechanic> findByAvailabilityStatusAndStatus(
            boolean availabilityStatus,
            String status
    );



}
