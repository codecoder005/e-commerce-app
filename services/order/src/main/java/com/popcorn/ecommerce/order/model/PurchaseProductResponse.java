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
public class PurchaseProductResponse {
    private UUID id;
    private Long quantity;
    private String name;
    private String description;
    private BigDecimal price;
}
