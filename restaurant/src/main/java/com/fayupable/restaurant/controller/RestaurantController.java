package com.fayupable.restaurant.controller;

import com.fayupable.restaurant.dto.RestaurantRequest;
import com.fayupable.restaurant.dto.RestaurantResponse;
import com.fayupable.restaurant.service.IRestaurantService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/api/v1/restaurants")
@RequiredArgsConstructor
public class RestaurantController {
    private final IRestaurantService restaurantService;

    @PostMapping
    public ResponseEntity<String> createRestaurant(
            @RequestBody @Valid RestaurantRequest restaurantRequest) {
        return ResponseEntity.ok(this.restaurantService.createRestaurant(restaurantRequest));
    }

    @PutMapping
    public ResponseEntity<Void> updateRestaurant(
            @RequestBody @Valid RestaurantRequest restaurantRequest) {
        this.restaurantService.updateRestaurant(restaurantRequest);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<RestaurantResponse>> findAllRestaurants() {
        return ResponseEntity.ok(this.restaurantService.findAllRestaurants());
    }

    @GetMapping("/exists/{restaurant-id}")
    public ResponseEntity<Boolean> existsById(@PathVariable("restaurant-id") String restaurantId) {
        return ResponseEntity.ok(this.restaurantService.existsById(restaurantId));
    }

    @GetMapping("/{restaurant-id}")
    public ResponseEntity<RestaurantResponse> findRestaurantById(@PathVariable("restaurant-id") String restaurantId) {
        return ResponseEntity.ok(this.restaurantService.findRestaurantById(restaurantId));
    }

    @DeleteMapping("/{restaurant-id}")
    public ResponseEntity<Void> deleteRestaurantById(@PathVariable("restaurant-id") String restaurantId) {
        this.restaurantService.deleteRestaurantById(restaurantId);
        return ResponseEntity.noContent().build();
    }


}
