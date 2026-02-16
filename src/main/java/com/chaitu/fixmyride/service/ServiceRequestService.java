package com.chaitu.fixmyride.service;

import com.chaitu.fixmyride.model.ServiceRequest;
import com.chaitu.fixmyride.repo.ServiceRequestRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServiceRequestService {

    @Autowired
    private ServiceRequestRepo repo;

    // Get all service requests
    public List<ServiceRequest> getAllRequests() {
        return repo.findAll();
    }

    // Get service request by ID
    public ServiceRequest getRequestById(int id) {
        return repo.findById(id).orElse(null);
    }

    // Create new service request
    public ServiceRequest addRequest(ServiceRequest request) {
        System.out.println("SAVING TO DB >>> " + request);
        return repo.save(request);
    }

    // Update selected fields (PUT)
    public ServiceRequest updateRequest(int id, ServiceRequest request) {

        ServiceRequest existing = repo.findById(id).orElse(null);

        if (existing == null) {
            return null;
        }

        // Update only allowed fields
        if (request.getProblem_description() != null) {
            existing.setProblem_description(request.getProblem_description());
        }

        if (request.getStatus() != null) {
            existing.setStatus(request.getStatus());
        }

        if (request.getAssigned_mechanic_id() != 0) {
            existing.setAssigned_mechanic_id(request.getAssigned_mechanic_id());
        }

        if (request.getAmount() != 0) {
            existing.setAmount(request.getAmount());
        }

        if (request.getPayment_status() != null) {
            existing.setPayment_status(request.getPayment_status());
        }

        return repo.save(existing);
    }

    public void deleteRequestById(int id) {
        repo.deleteById(id);
    }
}
