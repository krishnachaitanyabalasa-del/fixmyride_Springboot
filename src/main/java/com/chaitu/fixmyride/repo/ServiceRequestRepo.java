package com.chaitu.fixmyride.repo;

import com.chaitu.fixmyride.model.ServiceRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ServiceRequestRepo extends JpaRepository<ServiceRequest,Integer> {

    List<ServiceRequest> findByUsername(String username);

   // List<ServiceRequest> findByAssigned_mechanic_id(
     //       String assigned_mechanic_id);
   List<ServiceRequest> findByAssignedMechanicId(String assignedMechanicId);

    List<ServiceRequest> findByStatus(String status);
}
