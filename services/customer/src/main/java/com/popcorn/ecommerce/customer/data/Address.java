package com.popcorn.ecommerce.customer.data;

import lombok.*;
import org.springframework.validation.annotation.Validated;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Validated
public class Address {
    private String houseNumber;
    private String street;
    private String zipCode;
}
