package com.popcorn.ecommerce.customer.data;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document(value = "customers")
public class Customer {
    @Id
    private UUID id;
    private String name;

    private String email;
    private Address address;
}
