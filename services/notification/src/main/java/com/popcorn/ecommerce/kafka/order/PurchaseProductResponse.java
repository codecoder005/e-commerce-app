package com.popcorn.ecommerce.kafka.order;

import lombok.*;

import java.io.Serializable;
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
