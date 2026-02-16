package com.chaitu.fixmyride.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "payments")
@Data
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int payment_id;

    // Foreign Key
    private int request_id;

    private double amount;
    private String payment_method;   // UPI / Card / Cash
    private String payment_status;   // Paid / Pending / Failed
    private String transaction_id;

    private Date timestamp;
}
