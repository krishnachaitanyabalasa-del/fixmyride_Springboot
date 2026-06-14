package com.chaitu.fixmyride.service;

import com.chaitu.fixmyride.model.Mechanic;
import com.chaitu.fixmyride.repo.AdminRepo;
import com.chaitu.fixmyride.repo.MechanicRepo;
import com.chaitu.fixmyride.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.management.remote.JMXAuthenticator;
import java.util.List;

@Service
public class MechanicService {

    @Autowired
    private MechanicRepo repo;
    @Autowired
    private AdminRepo adminRepo;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private AuthenticationManager authManager;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    JWTService jwtService;


    public List<Mechanic> getAllMechanics() {
        return repo.findAll();
    }

    public Mechanic getMechanicById(int id) {
        return repo.findById(id).orElse(null);
    }

    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();


    public Mechanic addMechanic(Mechanic mechanic) {

        if(userRepo.findByUsername(mechanic.getUsername()) != null
                || repo.findByUsername(mechanic.getUsername()) != null
                || adminRepo.findByUsername(mechanic.getUsername()) != null) {

            throw new RuntimeException("Username already exists");
        }
        mechanic.setPassword(
                encoder.encode(mechanic.getPassword())
        );
        return repo.save(mechanic);
    }
    public List<Mechanic> getActiveMechanics() {
        return repo
                .findByAvailabilityStatusAndStatus(true,"Approved");
    }

    public List<Mechanic> getApprovedMechanics() {
        return repo.findByStatus("Approved");
    }
    public List<Mechanic> getPendingMechanics(){return repo.findByStatus("Pending");}
    public Mechanic getMechanicByEmail(String email){
        return repo.findByEmail(email);
    }
    public Mechanic getMechanicByUsername(String username) {
        return repo.findByUsername(username);
    }

    public String verify(String username, String password) {

        Mechanic mech = getMechanicByUsername(username);

        if (!repo.existsByUsername(username)) {
            return "usernameNotFound";
        }

        if(mech != null) {
            System.out.println("DB Status = [" + mech.getStatus() + "]");


            if("Pending".equalsIgnoreCase(mech.getStatus())) {
                System.out.println("Pending condition matched");
                return "pending";
            }

            if("Rejected".equalsIgnoreCase(mech.getStatus())) {
                return "rejected";
            }
        }

        try {
            Authentication authentication =
                    authManager.authenticate(
                            new UsernamePasswordAuthenticationToken(
                                    username,
                                    password));

            if (authentication.isAuthenticated()) {
                return jwtService.generateToken(username);
            }

        } catch (BadCredentialsException e) {
            return "passwordWrong";
        }


        return "failed";
    }

    public Mechanic updateMechanic(String username, Mechanic updatedData) {

        Mechanic mechanic = repo.findByUsername(username);

        if (mechanic == null) {
            throw new RuntimeException("Mechanic not found");
        }

        mechanic.setPhone_number(updatedData.getPhone_number());
        mechanic.setSkills(updatedData.getSkills());
        mechanic.setEmail(updatedData.getEmail());
        mechanic.setPassword(updatedData.getPassword());
        mechanic.setExperience_years(updatedData.getExperience_years());
        mechanic.setAvailabilityStatus(updatedData.isAvailabilityStatus());
        mechanic.setBase_location_lat(updatedData.getBase_location_lat());
        mechanic.setBase_location_long(updatedData.getBase_location_long());
        mechanic.setService_radius_km(updatedData.getService_radius_km());
        mechanic.setStatus(updatedData.getStatus());

        return repo.save(mechanic);
    }
}
