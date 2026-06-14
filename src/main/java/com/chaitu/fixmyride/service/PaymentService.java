package com.chaitu.fixmyride.service;

import com.chaitu.fixmyride.model.Payment;
import com.chaitu.fixmyride.model.ServiceRequest;
import com.chaitu.fixmyride.repo.PaymentRepo;
import com.chaitu.fixmyride.repo.ServiceRequestRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class PaymentService {

    @Autowired
    private PaymentRepo repo;

    @Autowired
    private ServiceRequestRepo serviceRequestRepo;

    public List<Payment> getAllPayments() {
        return repo.findAll();
    }

    public Payment getPaymentById(int id) {
        return repo.findById(id).orElse(null);
    }

    public Payment addPayment(Payment payment) throws Exception {

        Payment existingPayment =
                repo.findByRequestId(payment.getRequestId());

        if (existingPayment != null) {
            throw new Exception(
                    "Payment already exists for request id: "
                            + payment.getRequestId());
        }

        payment.setTimestamp(new Date());

        return repo.save(payment);
    }
    public Payment updatePayment(int id, Payment payment) {

        Payment existingPayment = repo.findById(id).orElse(null);

        if (existingPayment == null) {
            return null;
        }

        // Prevent modification of paid payments
        if ("Paid".equalsIgnoreCase(existingPayment.getPayment_status())) {
            throw new RuntimeException("Paid payments cannot be modified");
        }

        existingPayment.setRequestId(payment.getRequestId());
        existingPayment.setAmount(payment.getAmount());
        existingPayment.setPayment_method(payment.getPayment_method());
        existingPayment.setPayment_status(payment.getPayment_status());
        existingPayment.setTransaction_id(payment.getTransaction_id());
        existingPayment.setTimestamp(payment.getTimestamp());

        Payment savedPayment = repo.save(existingPayment);

        // Update ServiceRequest payment status
        ServiceRequest request =
                serviceRequestRepo.findById(existingPayment.getRequestId()).orElse(null);

        if (request != null) {
            request.setPayment_status(existingPayment.getPayment_status());

            if ("Paid".equalsIgnoreCase(existingPayment.getPayment_status())) {
                request.setAmount(savedPayment.getAmount());
                request.setStatus("Completed");
            }
            serviceRequestRepo.save(request);
        }

        return savedPayment;
    }

    public void deletePaymentById(int id) {
        repo.deleteById(id);
    }
}
