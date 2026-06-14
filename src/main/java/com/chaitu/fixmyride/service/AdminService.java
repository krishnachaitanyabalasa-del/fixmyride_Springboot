package com.chaitu.fixmyride.service;

import com.chaitu.fixmyride.model.Admin;
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
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminService {

    @Autowired
    private AdminRepo repo;

    @Autowired
    private UserRepo userRepo;


    @Autowired
    private MechanicRepo mechanicRepo;

    @Autowired
    private AuthenticationManager authManager;

    @Autowired
    private JWTService jwtService;

    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    public Admin addAdmin(Admin admin){

        if(userRepo.findByUsername(admin.getUsername()) != null
                || mechanicRepo.findByUsername(admin.getUsername()) != null
                || repo.findByUsername(admin.getUsername()) != null) {

            throw new RuntimeException("Username already exists");
        }


        admin.setPassword(
                encoder.encode(admin.getPassword())
        );
        admin.setRole("ADMIN");

        return repo.save(admin);
    }

    public String approveMechanic(String username) {

        Mechanic mechanic =
                mechanicRepo.findByUsername(username);

        if(mechanic == null) {
            return "Mechanic not found";
        }

        mechanic.setStatus("Approved");
        mechanicRepo.save(mechanic);

        return "Mechanic Approved Successfully";
    }

    public String rejectMechanic(String username) {

        Mechanic mechanic =
                mechanicRepo.findByUsername(username);

        if(mechanic == null) {
            return "Mechanic not found";
        }

        mechanic.setStatus("Rejected");
        mechanicRepo.save(mechanic);

        return "Mechanic Rejected Successfully";
    }

    public Admin getAdminByUsername(String username) {
        return repo.findById(username).orElse(null);
    }

    public List<Mechanic> getPendingMechanics() {
        return mechanicRepo.findByStatus("Pending");
    }

    public String verify(
            String username,
            String password){

        if (!repo.existsByUsername(username)) {
            return "usernameNotFound";
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


}

