package com.chaitu.fixmyride.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "mechanic_location")
@Data
public class MechanicLocation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    // Foreign Key
    @Column(name = "mechanic_id")
    private Integer mechanicId;


    // Current location
    private Double current_lat;
    private Double current_long;

    // Last updated timestamp
    private Date last_updated;
}
