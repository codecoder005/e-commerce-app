package com.popcorn.ecommerce.order.model;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class PurchaseProductRequest {
    @NotNull(message = "id is mandatory")
    private UUID id;

    @NotNull(message = "quantity is required")
    @Positive(message = "minimum quantity should be 1")
    private Long quantity;
}
