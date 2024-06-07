package com.popcorn.ecommerce.notification;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.UUID;

public interface NotificationRepository extends MongoRepository<Notification, UUID> {

}
