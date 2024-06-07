package com.popcorn.ecommerce.payment.controller;

import com.popcorn.ecommerce.payment.model.PaymentRequest;
import com.popcorn.ecommerce.payment.model.PaymentResponse;
import com.popcorn.ecommerce.payment.service.PaymentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/payments")
@RequiredArgsConstructor
@Slf4j
public class PaymentController {
    private final PaymentService paymentService;

    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<PaymentResponse> makePayment(
            @Valid @RequestBody PaymentRequest request
    ) {
        log.info("PaymentController::makePayment");
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(paymentService.makePayment(request));
    }
}
