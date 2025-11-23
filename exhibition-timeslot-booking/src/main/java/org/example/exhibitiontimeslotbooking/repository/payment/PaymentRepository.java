package org.example.exhibitiontimeslotbooking.repository.payment;

import org.example.exhibitiontimeslotbooking.entity.payment.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
}
