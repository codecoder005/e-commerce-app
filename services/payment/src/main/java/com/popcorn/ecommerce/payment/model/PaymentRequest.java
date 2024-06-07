package com.popcorn.ecommerce.payment.model;

import com.popcorn.ecommerce.payment.dto.CustomerDto;
import com.popcorn.ecommerce.payment.payment.PaymentMethod;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PaymentRequest {
    @NotNull(message = "orderId is required")
    private UUID orderId;

//    @NotNull(message = "customerId is required")
//    private UUID customerId;

    @Positive(message = "amount should be positive")
    private BigDecimal amount;

    @NotNull(message = "paymentMethod is required")
    private PaymentMethod paymentMethod;

    private CustomerDto customer;
}
