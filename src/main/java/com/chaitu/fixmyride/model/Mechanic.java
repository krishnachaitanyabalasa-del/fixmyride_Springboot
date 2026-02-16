package com.chaitu.fixmyride.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table
@Data
public class Mechanic {
    @Id
    private int mechanic_id;
    private String name;
    private String phone_number;
    private String skills;
    private String email;
    private String password;

    private int experience_years;
    private double rating;
    private boolean availability_status;
    private long base_location_lat;
    private long base_location_long;
    private int service_radius_km;
    private boolean profile_verified;


}
