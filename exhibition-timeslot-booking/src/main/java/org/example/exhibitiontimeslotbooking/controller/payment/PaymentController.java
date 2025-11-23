package org.example.exhibitiontimeslotbooking.controller.payment;

import lombok.RequiredArgsConstructor;
import org.example.exhibitiontimeslotbooking.common.constants.ApiMappingPattern;
import org.example.exhibitiontimeslotbooking.service.impl.payment.PaymentServiceImpl;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(ApiMappingPattern.Payments.ROOT)
@RequiredArgsConstructor
public class PaymentController {
    private final PaymentServiceImpl paymentService;
}
