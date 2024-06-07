package com.popcorn.ecommerce.payment.model;

import lombok.*;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PaymentResponse {
    private UUID paymentId;
    private BigDecimal amount;
    private String status;
}
