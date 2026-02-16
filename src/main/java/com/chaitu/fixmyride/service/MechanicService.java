package com.chaitu.fixmyride.service;

import com.chaitu.fixmyride.model.Mechanic;
import com.chaitu.fixmyride.repo.MechanicRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MechanicService {

    @Autowired
    private MechanicRepo repo;

    public List<Mechanic> getAllMechanics() {
        return repo.findAll();
    }

    public Mechanic getMechanicById(int id) {
        return repo.findById(id).orElse(null);
    }

    public Mechanic addMechanic(Mechanic mechanic) {
        return repo.save(mechanic);
    }

    public Mechanic getMechanicByEmail(String email){
        return repo.findByEmail(email);
    }
}
