package com.popcorn.ecommerce.order.model;

import lombok.*;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class PlaceOrderResponse {
    private UUID orderId;
    private BigDecimal amount;
}
