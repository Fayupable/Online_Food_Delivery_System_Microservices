package com.fayupable.restaurant.repository;

import com.fayupable.restaurant.entity.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IRestaurantRepository extends JpaRepository<Restaurant, String> {
}
