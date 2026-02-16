package com.chaitu.fixmyride.repo;

import com.chaitu.fixmyride.model.ServiceRequest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServiceRequestRepo extends JpaRepository<ServiceRequest,Integer> {
}
