package com.popcorn.ecommerce.customer;

import com.popcorn.ecommerce.customer.data.Customer;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.UUID;

public interface CustomerRepository extends MongoRepository<Customer, UUID> {

}
