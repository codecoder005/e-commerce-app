package com.popcorn.ecommerce.product.model;

import lombok.*;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateProductResponse {
    private UUID id;
    private String status;
}
