package com.popcorn.ecommerce.kafka.dto;

import lombok.*;
import org.springframework.validation.annotation.Validated;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Validated
@ToString
public class CustomerDto {
    private UUID id;
    private String name;
    private String email;
}
