package com.chaitu.fixmyride.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Table(name = "users")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    private Integer username;
    private String full_name;
    private String phoneNumber;
    private String email;
    private String password;
    private String address;

    private double location_lat;
    private double location_long;
    private Date created_at;
}
