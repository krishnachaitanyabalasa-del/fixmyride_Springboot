package com.chaitu.fixmyride.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "service_request")
@Data
public class ServiceRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer request_id;

    // Foreign Keys (IDs only)
    private Integer username;
    private Integer assigned_mechanic_id;

    // Vehicle info
    private String vehicle_type;   // Car / Bike / Auto / Truck
    private String vehicle_number;

    // Problem details
    private String problem_description;

    // Location
    private Double current_location_lat;
    private Double current_location_long;

    // Request info

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date requested_time;
    private String status;         // Pending, Assigned, In Progress, Completed, Cancelled

    // Payment
    private double amount;         // optional
    private String payment_status; // Paid / Unpaid / Pending
    private String location;


}
