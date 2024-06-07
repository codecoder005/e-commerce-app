package com.popcorn.ecommerce.order.client;

import com.popcorn.ecommerce.order.exception.AppException;
import com.popcorn.ecommerce.order.model.PurchaseProductRequest;
import com.popcorn.ecommerce.order.model.PurchaseProductResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static org.springframework.http.HttpHeaders.CONTENT_TYPE;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductClient {
    private final RestTemplate restTemplate;

    @Value("${application.config.product-url}")
    private String productUrl;

    public List<PurchaseProductResponse> purchaseProducts(List<PurchaseProductRequest> cart) {
        HttpHeaders headers = new HttpHeaders();
        headers.set(CONTENT_TYPE, APPLICATION_JSON_VALUE);

        HttpEntity<List<PurchaseProductRequest>> requestEntity = new HttpEntity<>(cart, headers);
        ParameterizedTypeReference<List<PurchaseProductResponse>> responseType = new ParameterizedTypeReference<>() {};

        ResponseEntity<List<PurchaseProductResponse>> responseEntity = restTemplate.exchange(
                productUrl + "/purchase",
                HttpMethod.POST,
                requestEntity,
                responseType
        );
        if(responseEntity.getStatusCode().isError()) {
            throw new AppException("An occurred while processing the purchase products request " + responseEntity.getStatusCode());
        }
        return responseEntity.getBody();
    }
}
