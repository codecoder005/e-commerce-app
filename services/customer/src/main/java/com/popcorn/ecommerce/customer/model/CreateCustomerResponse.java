package com.popcorn.ecommerce.customer.model;

import lombok.*;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateCustomerResponse {
    private UUID id;
}
