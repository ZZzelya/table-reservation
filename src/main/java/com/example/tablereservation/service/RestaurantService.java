package com.example.tablereservation.service;

import com.example.tablereservation.dto.RestaurantCreateDto;
import com.example.tablereservation.dto.RestaurantDto;
import com.example.tablereservation.service.mapper.RestaurantMapper;
import com.example.tablereservation.model.Restaurant;
import com.example.tablereservation.repository.RestaurantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RestaurantService {

    private final RestaurantRepository restaurantRepository;
    private final RestaurantMapper restaurantMapper;

    public List<RestaurantDto> getAllRestaurants() {
        return restaurantRepository.findAll().stream()
                .map(restaurantMapper::toDto)
                .collect(Collectors.toList());
    }

    public RestaurantDto getRestaurantById(Long id) {
        return restaurantRepository.findById(id)
                .map(restaurantMapper::toDto)
                .orElse(null);
    }

    public List<RestaurantDto> getRestaurantsByCuisine(String cuisineType) {
        return restaurantRepository.findAll().stream()
                .filter(r -> r.getCuisineType().equalsIgnoreCase(cuisineType))
                .map(restaurantMapper::toDto)
                .collect(Collectors.toList());
    }

    public RestaurantDto createRestaurant(RestaurantCreateDto createDto) {
        Restaurant restaurant = restaurantMapper.toEntity(createDto);
        Restaurant savedRestaurant = restaurantRepository.save(restaurant);
        return restaurantMapper.toDto(savedRestaurant);
    }
}