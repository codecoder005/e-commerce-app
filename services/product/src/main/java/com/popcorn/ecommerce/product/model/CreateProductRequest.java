package com.popcorn.ecommerce.product.model;

import jakarta.validation.constraints.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateProductRequest {
    @NotNull(message = "name is required")
    @NotEmpty(message = "name can not be blank")
    private String name;

    @NotNull(message = "description is required")
    @NotEmpty(message = "description can not be blank")
    private String description;

    @Positive(message = "availableQuantity should at least be 1")
    private String availableQuantity;

    @Positive(message = "price should be positive")
    @DecimalMin(value = "0.001", message = "minimum price of product is 0.001")
    private BigDecimal price;

    @NotNull(message = "categoryId is required")
    private UUID categoryId;
}
