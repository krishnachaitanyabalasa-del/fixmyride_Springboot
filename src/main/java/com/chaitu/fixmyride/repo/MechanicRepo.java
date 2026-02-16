package com.chaitu.fixmyride.repo;

import com.chaitu.fixmyride.model.Mechanic;
import com.chaitu.fixmyride.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MechanicRepo extends JpaRepository<Mechanic,Integer> {
    Mechanic findByEmail(String email);
    List<Mechanic> findByMechanicId(String mechanicId);

}
