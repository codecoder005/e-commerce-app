package com.popcorn.ecommerce.customer.dto;

import com.popcorn.ecommerce.customer.data.Address;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CustomerDto {
    private UUID id;
    private String name;

    private String email;
    private Address address;
}
