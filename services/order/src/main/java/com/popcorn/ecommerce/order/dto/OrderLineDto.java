package com.popcorn.ecommerce.order.dto;

import lombok.*;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderLineDto {
    private UUID orderLineId;
    private UUID orderId;

    private UUID productId;
    private Long quantity;
}
