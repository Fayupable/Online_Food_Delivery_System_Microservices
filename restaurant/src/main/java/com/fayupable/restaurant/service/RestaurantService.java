package com.fayupable.restaurant.service;

import com.fayupable.restaurant.dto.RestaurantRequest;
import com.fayupable.restaurant.dto.RestaurantResponse;
import com.fayupable.restaurant.entity.Restaurant;
import com.fayupable.restaurant.exception.RestaurantNotFoundException;
import com.fayupable.restaurant.mapper.RestaurantMapper;
import com.fayupable.restaurant.repository.IRestaurantRepository;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RestaurantService implements IRestaurantService {
    private final IRestaurantRepository restaurantRepository;
    private final RestaurantMapper restaurantMapper;

    @Override
    public String createRestaurant(RestaurantRequest restaurantRequest) {
        var restaurant = this.restaurantRepository.save(restaurantMapper.toRestaurant(restaurantRequest));
        return restaurant.getId();
    }

    @Override
    public void updateRestaurant(RestaurantRequest restaurantRequest) {
        var restaurant = this.restaurantRepository.findById(restaurantRequest.id())
                .orElseThrow(() -> new RestaurantNotFoundException(String.format("Restaurant with id %s not found", restaurantRequest.id())));
        mergeRestaurant(restaurant, restaurantRequest);
        this.restaurantRepository.save(restaurant);
    }

    private void mergeRestaurant(Restaurant restaurant, RestaurantRequest restaurantRequest) {
        if (StringUtils.isNotBlank(restaurantRequest.name())) {
            restaurant.setName(restaurantRequest.name());
        }
        if (StringUtils.isNotBlank(restaurantRequest.description())) {
            restaurant.setDescription(restaurantRequest.description());
        }
        if (StringUtils.isNotBlank(restaurantRequest.email())) {
            restaurant.setEmail(restaurantRequest.email());
        }
        if (StringUtils.isNotBlank(restaurantRequest.contactNumber())) {
            restaurant.setContactNumber(restaurantRequest.contactNumber());
        }
        if (restaurantRequest.address() != null) {
            restaurant.setAddress(restaurantRequest.address());
        }
    }

    @Override
    public List<RestaurantResponse> findAllRestaurants() {
        return this.restaurantRepository.findAll().stream()
                .map(this.restaurantMapper::fromRestaurant)
                .collect(Collectors.toList());
    }

    @Override
    public RestaurantResponse findRestaurantById(String id) {
        return this.restaurantRepository.findById(id)
                .map(this.restaurantMapper::fromRestaurant)
                .orElseThrow(() -> new RestaurantNotFoundException(String.format("Restaurant with id %s not found", id)));
    }

    @Override
    public boolean existsById(String id) {
        return this.restaurantRepository.findById(id).isPresent();
    }

    @Override
    public void deleteRestaurantById(String id) {
        this.restaurantRepository.deleteById(id);
    }
}
