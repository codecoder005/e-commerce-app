package com.popcorn.ecommerce.customer.service;

import com.popcorn.ecommerce.customer.CustomerRepository;
import com.popcorn.ecommerce.customer.data.Customer;
import com.popcorn.ecommerce.customer.dto.CustomerDto;
import com.popcorn.ecommerce.customer.exception.CustomerNotFoundException;
import com.popcorn.ecommerce.customer.model.CreateCustomerRequest;
import com.popcorn.ecommerce.customer.model.CreateCustomerResponse;
import com.popcorn.ecommerce.customer.model.UpdateCustomerRequest;
import com.popcorn.ecommerce.customer.model.UpdateCustomerResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class CustomerService {
    private final CustomerRepository customerRepository;
    private final ModelMapper modelMapper;

    public CreateCustomerResponse createCustomer(CreateCustomerRequest request) {
        log.info("CustomerService::createCustomer");
        Customer customer = modelMapper.map(request, Customer.class);
        customer.setId(UUID.randomUUID());
        customer = customerRepository.save(customer);
        return CreateCustomerResponse.builder()
                .id(customer.getId())
                .build();
    }

    public UpdateCustomerResponse updateCustomer(UUID id, UpdateCustomerRequest request) {
        log.info("CustomerService::updateCustomer");
        var customer = customerRepository.findById(id)
                .orElseThrow(() -> new CustomerNotFoundException("Update failed. no customer found with id: " + id));

        if(StringUtils.isNotBlank(request.getName())) {
            customer.setName(request.getName());
        }
        if(StringUtils.isNotBlank(request.getEmail())) {
            customer.setEmail(request.getEmail());
        }
        if(request.getAddress() != null) {
            customer.setAddress(request.getAddress());
        }
        customerRepository.save(customer);
        return UpdateCustomerResponse.builder()
                .updateStatus("SUCCESS")
                .build();
    }

    public List<CustomerDto> getAllCustomers() {
        log.info("CustomerService::getAllCustomers");
        return customerRepository.findAll().stream()
                .map(customer -> modelMapper.map(customer, CustomerDto.class))
                .toList();
    }

    public CustomerDto getCustomerById(UUID id) {
        log.info("CustomerService::getCustomerById");
        var customer = customerRepository.findById(id)
                .orElseThrow(() -> new CustomerNotFoundException("Customer not found with id: " + id));
        return modelMapper.map(customer, CustomerDto.class);
    }

    public void deleteCustomer(UUID id) {
        log.info("CustomerService::deleteCustomer");
        customerRepository.deleteById(id);
    }
}
