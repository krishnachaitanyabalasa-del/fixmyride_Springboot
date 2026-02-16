package com.chaitu.fixmyride.service;

import com.chaitu.fixmyride.model.Payment;
import com.chaitu.fixmyride.repo.PaymentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class PaymentService {

    @Autowired
    private PaymentRepo repo;

    public List<Payment> getAllPayments() {
        return repo.findAll();
    }

    public Payment getPaymentById(int id) {
        return repo.findById(id).orElse(null);
    }

    public Payment addPayment(Payment payment) {
        payment.setTimestamp(new Date());
        return repo.save(payment);
    }

    public void deletePaymentById(int id) {
        repo.deleteById(id);
    }
}
