package com.popcorn.ecommerce.product.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ExceptionResponse {
    private String errorMessage;
    private LocalDateTime timestamp = LocalDateTime.now();
    private Integer statusCode;
    private Map<String, Object> errors;
}
