package com.popcorn.ecommerce.payment.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
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
    @NotNull(message = "customerId is required")
    private UUID id;

    @NotNull(message = "name is required")
    @NotBlank(message = "name can not be blank")
    @NotEmpty(message = "name is empty")
    private String name;

    @NotNull(message = "email is required")
    @Email(message = "invalid email")
    private String email;
}
