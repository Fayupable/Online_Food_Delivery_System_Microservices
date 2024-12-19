package com.fayupable.restaurant.repository;

import com.fayupable.restaurant.entity.Restaurant;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface IRestaurantRepository extends MongoRepository<Restaurant, String> {
}
