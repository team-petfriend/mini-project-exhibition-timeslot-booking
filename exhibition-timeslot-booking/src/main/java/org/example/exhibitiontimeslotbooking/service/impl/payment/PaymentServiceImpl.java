package org.example.exhibitiontimeslotbooking.service.impl.payment;

import lombok.RequiredArgsConstructor;
import org.example.exhibitiontimeslotbooking.repository.payment.PaymentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly=true)
public class PaymentServiceImpl {
    private final PaymentRepository paymentRepository;
}
