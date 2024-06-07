package com.popcorn.ecommerce.payment.model;


import com.popcorn.ecommerce.order.dto.CustomerDto;
import com.popcorn.ecommerce.order.entity.PaymentMethod;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class PaymentRequest implements Serializable {
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
