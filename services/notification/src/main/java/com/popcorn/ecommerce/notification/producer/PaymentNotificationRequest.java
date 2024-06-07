package com.popcorn.ecommerce.notification.producer;

import com.popcorn.ecommerce.kafka.dto.CustomerDto;
import com.popcorn.ecommerce.kafka.payment.PaymentMethod;
import lombok.*;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class PaymentNotificationRequest {
    private UUID orderId;
    private UUID customerId;
    private BigDecimal amount;
    private PaymentMethod paymentMethod;
    private CustomerDto customer;
}
