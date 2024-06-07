package com.popcorn.ecommerce.product.dto;

import lombok.*;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductDto {
    private UUID id;

    private Long availableQuantity;
    private String name;
    private String description;
    private BigDecimal price;
}
