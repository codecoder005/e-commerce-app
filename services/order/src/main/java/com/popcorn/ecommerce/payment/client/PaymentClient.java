package com.popcorn.ecommerce.payment.client;

import com.popcorn.ecommerce.payment.model.PaymentRequest;
import com.popcorn.ecommerce.payment.model.PaymentResponse;
import jakarta.validation.Valid;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(
        name = "payment-service",
        url = "${application.config.payment-url}"
)
public interface PaymentClient {
    @PostMapping
    PaymentResponse makePayment(@Valid @RequestBody PaymentRequest paymentRequest);
}
