package com.popcorn.ecommerce.kafka.payment;

import com.popcorn.ecommerce.kafka.dto.CustomerDto;
import lombok.*;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class PaymentConfirmation {
    private UUID orderId;
    private UUID customerId;
    private BigDecimal amount;
    private PaymentMethod paymentMethod;
    private CustomerDto customer;
}
