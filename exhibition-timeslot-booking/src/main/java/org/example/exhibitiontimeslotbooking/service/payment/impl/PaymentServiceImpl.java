package org.example.exhibitiontimeslotbooking.service.payment.impl;

import lombok.RequiredArgsConstructor;
import org.example.exhibitiontimeslotbooking.repository.payment.PaymentRepository;
import org.example.exhibitiontimeslotbooking.service.payment.PaymentService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly=true)
public class PaymentServiceImpl implements PaymentService {
    private final PaymentRepository paymentRepository;
}
