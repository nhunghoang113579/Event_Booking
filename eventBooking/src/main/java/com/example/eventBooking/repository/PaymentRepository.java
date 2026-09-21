package com.example.eventBooking.repository;

import com.example.eventBooking.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;


public interface PaymentRepository extends JpaRepository<Payment,Long> {

}
