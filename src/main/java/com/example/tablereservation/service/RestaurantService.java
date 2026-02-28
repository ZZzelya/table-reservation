package com.example.tablereservation.service;

import com.example.tablereservation.dto.request.RestaurantCreateDto;
import com.example.tablereservation.dto.response.RestaurantDto;
import com.example.tablereservation.dto.response.RestaurantWithTablesDto;
import com.example.tablereservation.exception.ResourceNotFoundException;
import com.example.tablereservation.model.entity.Restaurant;
import com.example.tablereservation.repository.RestaurantRepository;
import com.example.tablereservation.service.mapper.RestaurantMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class RestaurantService {

    private final RestaurantRepository restaurantRepository;
    private final RestaurantMapper restaurantMapper;

    @Transactional(readOnly = true)
    public List<RestaurantDto> getAllRestaurants() {
        return restaurantMapper.toDtoList(restaurantRepository.findAll());
    }

    @Transactional(readOnly = true)
    public RestaurantDto getRestaurantById(Long id) {
        Restaurant restaurant = findRestaurantOrThrow(id);
        return restaurantMapper.toDto(restaurant);
    }

    @Transactional(readOnly = true)
    public RestaurantWithTablesDto getRestaurantWithTables(Long id) {
        Restaurant restaurant = restaurantRepository.findByIdWithTables(id)
            .orElseThrow(() -> new ResourceNotFoundException("Restaurant not found with id: " + id));
        return restaurantMapper.toDtoWithTables(restaurant);
    }

    @Transactional(readOnly = true)
    public List<RestaurantDto> getRestaurantsByCuisine(String cuisineType) {
        return restaurantMapper.toDtoList(restaurantRepository.findByCuisineTypeIgnoreCase(cuisineType));
    }

    @Transactional
    public RestaurantDto createRestaurant(RestaurantCreateDto createDto) {
        if (restaurantRepository.existsByPhoneNumber(createDto.getPhoneNumber())) {
            throw new IllegalArgumentException("Restaurant with this phone number already exists");
        }
        Restaurant restaurant = restaurantMapper.toEntity(createDto);
        Restaurant savedRestaurant = restaurantRepository.save(restaurant);
        return restaurantMapper.toDto(savedRestaurant);
    }

    @Transactional
    public RestaurantDto updateRestaurant(Long id, RestaurantCreateDto updateDto) {
        Restaurant restaurant = findRestaurantOrThrow(id);
        restaurantMapper.updateEntity(restaurant, updateDto);
        Restaurant updatedRestaurant = restaurantRepository.save(restaurant);
        return restaurantMapper.toDto(updatedRestaurant);
    }

    @Transactional
    public void deleteRestaurant(Long id) {
        Restaurant restaurant = findRestaurantOrThrow(id);
        restaurantRepository.delete(restaurant);
    }

    @Transactional(readOnly = true)
    public void demonstrateNPlusOneProblem(String cuisineType) {
        log.info("=== Demonstrating N+1 problem ===");
        List<Restaurant> restaurants = restaurantRepository.findByCuisineTypeIgnoreCase(cuisineType);
        for (Restaurant restaurant : restaurants) {
            int tableCount = restaurant.getTables().size();
            log.info("Restaurant {} has {} tables", restaurant.getName(), tableCount);
        }
    }

    @Transactional(readOnly = true)
    public void solveNPlusOneWithFetchJoin(String cuisineType) {
        log.info("=== Solving N+1 with fetch join ===");
        List<Restaurant> restaurants = restaurantRepository.findByCuisineTypeWithTables(cuisineType);
        for (Restaurant restaurant : restaurants) {
            int tableCount = restaurant.getTables().size();
            log.info("Restaurant {} has {} tables", restaurant.getName(), tableCount);
        }
    }

    private Restaurant findRestaurantOrThrow(Long id) {
        return restaurantRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Restaurant not found with id: " + id));
    }
}