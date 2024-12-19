package com.fayupable.restaurant.service;

import com.fayupable.restaurant.dto.RestaurantRequest;
import com.fayupable.restaurant.dto.RestaurantResponse;

import java.util.List;

public interface IRestaurantService {
    String createRestaurant(RestaurantRequest restaurantRequest);

    void updateRestaurant(RestaurantRequest restaurantRequest);

    List<RestaurantResponse> findAllRestaurants();

    RestaurantResponse findRestaurantById(String id);

    boolean existsById(String id);

    void deleteRestaurantById(String id);
}
