package com.popcorn.ecommerce.order.dto;

import lombok.*;
import org.springframework.validation.annotation.Validated;

import java.io.Serializable;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@Validated
public class CustomerDto implements Serializable {
    private UUID id;
    private String name;

    private String email;
}
