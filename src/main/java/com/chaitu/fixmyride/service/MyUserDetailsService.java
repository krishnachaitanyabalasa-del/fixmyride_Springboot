package com.chaitu.fixmyride.service;


import com.chaitu.fixmyride.model.*;
import com.chaitu.fixmyride.repo.AdminRepo;
import com.chaitu.fixmyride.repo.MechanicRepo;
import com.chaitu.fixmyride.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private MechanicRepo mechanicRepo;

    @Autowired
    private AdminRepo adminRepo;

    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {

        System.out.println("Searching username = " + username);

        User user = userRepo.findByUsername(username);
        System.out.println("User = " + user);

        if(user != null){
            return new UserPrincipal(user);
        }

        Mechanic mechanic = mechanicRepo.findByUsername(username);
        System.out.println("Mechanic = " + mechanic);

        if(mechanic != null){
            return new MechanicPrincipal(mechanic);
        }

        System.out.println("Checking Admin");

        Admin admin = adminRepo.findByUsername(username);
        System.out.println("Admin = " + admin);

        if(admin != null){
            return new AdminPrincipal(admin);
        }

        throw new UsernameNotFoundException("User not found");
    }
}