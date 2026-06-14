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
    private String username;
    @Column(name = "assigned_mechanic_id")
    private String assignedMechanicId;

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

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    // Payment
    private double amount;         // optional
    private String payment_status; // Paid / Unpaid / Pending
    private String location;

    private String service_category;

    public Integer getRequest_id() {
        return request_id;
    }

    public void setRequest_id(Integer request_id) {
        this.request_id = request_id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAssigned_mechanic_id() {
        return assignedMechanicId;
    }

    public void setAssigned_mechanic_id(String assigned_mechanic_id) {
        this.assignedMechanicId = assigned_mechanic_id;
    }

    public String getVehicle_type() {
        return vehicle_type;
    }

    public void setVehicle_type(String vehicle_type) {
        this.vehicle_type = vehicle_type;
    }

    public String getVehicle_number() {
        return vehicle_number;
    }

    public void setVehicle_number(String vehicle_number) {
        this.vehicle_number = vehicle_number;
    }

    public String getProblem_description() {
        return problem_description;
    }

    public void setProblem_description(String problem_description) {
        this.problem_description = problem_description;
    }

    public Double getCurrent_location_lat() {
        return current_location_lat;
    }

    public void setCurrent_location_lat(Double current_location_lat) {
        this.current_location_lat = current_location_lat;
    }

    public Double getCurrent_location_long() {
        return current_location_long;
    }

    public void setCurrent_location_long(Double current_location_long) {
        this.current_location_long = current_location_long;
    }

    public Date getRequested_time() {
        return requested_time;
    }

    public void setRequested_time(Date requested_time) {
        this.requested_time = requested_time;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPayment_status() {
        return payment_status;
    }

    public void setPayment_status(String payment_status) {
        this.payment_status = payment_status;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
