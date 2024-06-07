package com.popcorn.ecommerce.customer.model;

import com.popcorn.ecommerce.customer.data.Address;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UpdateCustomerRequest {
    private String name;
    private String email;
    private Address address;
}
