package com.example.tablereservation.controller;

import com.example.tablereservation.dto.RestaurantCreateDto;
import com.example.tablereservation.dto.RestaurantDto;
import com.example.tablereservation.service.RestaurantService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/restaurants")
@RequiredArgsConstructor
public class RestaurantController {

    private final RestaurantService restaurantService;

    @GetMapping
    public List<RestaurantDto> getRestaurants(
        @RequestParam(value = "cuisine", required = false) String cuisineType) {
        if (cuisineType != null && !cuisineType.isEmpty()) {
            return restaurantService.getRestaurantsByCuisine(cuisineType);
        } else {
            return restaurantService.getAllRestaurants();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<RestaurantDto> getRestaurantById(@PathVariable Long id) {
        RestaurantDto restaurant = restaurantService.getRestaurantById(id);

        if (restaurant == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(restaurant);
    }

    @PostMapping
    public RestaurantDto createRestaurant(@RequestBody RestaurantCreateDto createDto) {
        return restaurantService.createRestaurant(createDto);
    }
}