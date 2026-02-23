package com.example.tablereservation.controller;

import com.example.tablereservation.dto.RestaurantCreateDto;
import com.example.tablereservation.dto.RestaurantDto;
import com.example.tablereservation.service.RestaurantService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class RestaurantController {

    private final RestaurantService restaurantService;

    // GET все рестораны или с фильтром
    @GetMapping
    public List<RestaurantDto> getRestaurants(
            @RequestParam(value = "cuisine", required = false) String cuisineType) {
        if (cuisineType != null && !cuisineType.isEmpty()) {
            return restaurantService.getRestaurantsByCuisine(cuisineType);
        } else {
            return restaurantService.getAllRestaurants();
        }
    }

    // GET по ID
    @GetMapping("/{id}")
    public RestaurantDto getRestaurantById(@PathVariable Long id) {
        return restaurantService.getRestaurantById(id);
    }

    // POST создание
    @PostMapping
    public RestaurantDto createRestaurant(@RequestBody RestaurantCreateDto createDto) {
        return restaurantService.createRestaurant(createDto);
    }
}