package com.chaitu.fixmyride.repo;

import com.chaitu.fixmyride.model.MechanicLocation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MechanicLocationRepo
        extends JpaRepository<MechanicLocation, Integer> {
    MechanicLocation findByMechanicId(int mechanicId);
}
