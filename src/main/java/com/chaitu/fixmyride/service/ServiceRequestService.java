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

    public List<ServiceRequest> getAllRequests() {
        return repo.findAll();
    }

    public ServiceRequest getRequestById(int id) {
        return repo.findById(id).orElse(null);
    }

    public ServiceRequest addRequest(ServiceRequest request) {
        System.out.println("SAVING TO DB >>> " + request);
        return repo.save(request);
    }

    public ServiceRequest updateRequest(
            int id,
            ServiceRequest request) {
        System.out.println("Received Request = " + request);
        System.out.println("Assigned Mechanic = " +
                request.getAssigned_mechanic_id());

        ServiceRequest existing =
                repo.findById(id).orElse(null);

        if (existing == null) {
            return null;
        }

        existing.setProblem_description(request.getProblem_description());
        existing.setStatus(request.getStatus());
        existing.setAssigned_mechanic_id(request.getAssigned_mechanic_id());
        existing.setAmount(request.getAmount());
        existing.setPayment_status(request.getPayment_status());


        return repo.save(existing);
    }
    public List<ServiceRequest> getPendingRequests() {
        return repo.findByStatus("Pending");
    }
    public List<ServiceRequest> getRequestsByUsername(String username){
        return repo.findByUsername(username);
    }

    public List<ServiceRequest> getRequestsByMechanic(String mechanicId) {
        return repo.findByAssignedMechanicId(mechanicId);
    }
    public void deleteRequestById(int id) {
        repo.deleteById(id);
    }
}