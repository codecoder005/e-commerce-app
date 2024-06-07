package com.popcorn.ecommerce.customer.model;

import com.popcorn.ecommerce.customer.data.Address;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateCustomerRequest {
    @NotNull(message = "name is required")
    private String name;

    @NotNull(message = "email is required")
    @Email(message = "customer email is not valid")
    private String email;

    private Address address;
}
