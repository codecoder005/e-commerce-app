package com.popcorn.ecommerce.kafka.order;

import com.popcorn.ecommerce.kafka.payment.PaymentMethod;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class OrderConfirmation {
    private UUID orderId;
    private UUID customerId;
    private BigDecimal amount;
    private PaymentMethod paymentMethod;

    private String customerEmail;
    private String customerName;
    private List<PurchaseProductResponse> products;
}
