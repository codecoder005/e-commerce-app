package com.popcorn.ecommerce.product.service;

import com.popcorn.ecommerce.product.dto.ProductDto;
import com.popcorn.ecommerce.product.entity.CategoryEntity;
import com.popcorn.ecommerce.product.entity.ProductEntity;
import com.popcorn.ecommerce.product.exception.ProductNotFoundException;
import com.popcorn.ecommerce.product.exception.PurchaseFailedException;
import com.popcorn.ecommerce.product.model.CreateProductRequest;
import com.popcorn.ecommerce.product.model.CreateProductResponse;
import com.popcorn.ecommerce.product.model.PurchaseProductRequest;
import com.popcorn.ecommerce.product.model.PurchaseProductResponse;
import com.popcorn.ecommerce.product.repository.ProductRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductService {
    private final ProductRepository productRepository;
    private final ModelMapper modelMapper;

    public CreateProductResponse createProduct(CreateProductRequest request) {
        log.info("ProductService::createProduct");
        ProductEntity productEntity = modelMapper.map(request, ProductEntity.class);
        productEntity.setCategory(CategoryEntity.builder().id(request.getCategoryId()).build());
        productEntity = productRepository.save(productEntity);
        return CreateProductResponse.builder()
                .id(productEntity.getId())
                .status("PRODUCT_CREATED")
                .build();
    }

    public List<ProductDto> getAllProducts() {
        log.info("ProductService::getAllProducts");
        return productRepository.findAll().stream()
                .map(entity -> modelMapper.map(entity, ProductDto.class)).toList();
    }

    public ProductDto getProductById(UUID id) {
        log.info("ProductService::getProductById");
        return productRepository.findById(id)
                .map(entity -> modelMapper.map(entity, ProductDto.class))
                .orElseThrow(() -> new ProductNotFoundException("No product found with id: " + id));
    }

    @Transactional
    public List<PurchaseProductResponse> purchaseProducts(List<PurchaseProductRequest> request) {
        log.info("ProductService::purchaseProducts");
        var productIds = request.stream()
                .map(PurchaseProductRequest::getId)
                .toList();
        var availableProducts = productRepository.findAllByIdInOrderById(productIds);
        if(productIds.size() != availableProducts.size()) {
            throw new PurchaseFailedException("One or more request does not exists in the store");
        }

        List<PurchaseProductResponse> purchaseProductResponses = new ArrayList<>();
        for(var r: request) {
            var optional = productRepository.findById(r.getId());
            if(optional.isPresent()) {
                var product = optional.get();
                if(product.getAvailableQuantity() < r.getQuantity()) {
                    throw new PurchaseFailedException(String.format(
                            "Required quantity %d for product %s is not available",
                            r.getQuantity(),
                            r.getId()
                    ));
                }
                var newAvailableQuantity = product.getAvailableQuantity() - r.getQuantity();
                product.setAvailableQuantity(newAvailableQuantity);
                var purchaseResponse = PurchaseProductResponse.builder()
                        .id(product.getId())
                        .name(product.getName())
                        .quantity(r.getQuantity())
                        .price(product.getPrice())
                        .description(product.getDescription())
                        .build();
                purchaseProductResponses.add(purchaseResponse);
            }
        }
        return purchaseProductResponses;
    }
}
