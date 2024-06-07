package com.popcorn.ecommerce.order.dto;

import com.popcorn.ecommerce.order.entity.PaymentMethod;
import com.popcorn.ecommerce.order.orderline.OrderLine;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderDto {
    private UUID orderId;
    private UUID customerId;

    private BigDecimal amount;
    private PaymentMethod paymentMethod;

    private List<OrderLine> orderLine;

    private LocalDateTime orderDate;
    private LocalDateTime lastModifiedDate;
}
