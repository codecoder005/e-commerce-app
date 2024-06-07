package com.popcorn.ecommerce.order.model;

import com.popcorn.ecommerce.order.entity.PaymentMethod;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PlaceOrderRequest {
    @NotNull(message = "customerId is required")
    private UUID customerId;

    @NotNull(message = "no products selected to place order")
    @NotEmpty(message = "no products selected to place order")
    private List<PurchaseProductRequest> products;

    @NotNull(message = "paymentMethod is required")
    private PaymentMethod paymentMethod;
}
