package com.example.tablereservation.service;

import com.example.tablereservation.dto.RestaurantCreateDto;
import com.example.tablereservation.dto.RestaurantDto;
import com.example.tablereservation.model.Restaurant;
import com.example.tablereservation.repository.RestaurantRepository;
import com.example.tablereservation.service.mapper.RestaurantMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RestaurantService {

    private final RestaurantRepository restaurantRepository;
    private final RestaurantMapper restaurantMapper;

    public List<RestaurantDto> getAllRestaurants() {
        List<Restaurant> restaurants = restaurantRepository.findAll();
        List<RestaurantDto> restaurantDto = new ArrayList<>();

        for (Restaurant restaurant : restaurants) {
            restaurantDto.add(restaurantMapper.toDto(restaurant));
        }

        return restaurantDto;
    }

    public RestaurantDto getRestaurantById(Long id) {
        Restaurant restaurant = restaurantRepository.findById(id).orElse(null);

        if (restaurant != null) {
            return restaurantMapper.toDto(restaurant);
        }

        return null;
    }

    public List<RestaurantDto> getRestaurantsByCuisine(String cuisineType) {
        List<Restaurant> allRestaurants = restaurantRepository.findAll();
        List<RestaurantDto> filteredRestaurants = new ArrayList<>();

        for (Restaurant restaurant : allRestaurants) {
            if (restaurant.getCuisineType().equalsIgnoreCase(cuisineType)) {
                filteredRestaurants.add(restaurantMapper.toDto(restaurant));
            }
        }

        return filteredRestaurants;
    }

    public RestaurantDto createRestaurant(RestaurantCreateDto createDto) {
        Restaurant restaurant = restaurantMapper.toEntity(createDto);
        Restaurant savedRestaurant = restaurantRepository.save(restaurant);
        return restaurantMapper.toDto(savedRestaurant);
    }
}