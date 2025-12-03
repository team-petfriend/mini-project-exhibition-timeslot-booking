package org.example.exhibitiontimeslotbooking.controller.payment;

import lombok.RequiredArgsConstructor;
import org.example.exhibitiontimeslotbooking.common.constants.ApiMappingPattern;
import org.example.exhibitiontimeslotbooking.service.payment.PaymentService;
import org.example.exhibitiontimeslotbooking.service.payment.impl.PaymentServiceImpl;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(ApiMappingPattern.Payments.ROOT)
@RequiredArgsConstructor
public class PaymentController {
    private final PaymentService paymentService;


}
