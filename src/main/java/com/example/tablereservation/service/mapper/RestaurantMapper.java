package com.example.tablereservation.service.mapper;

import com.example.tablereservation.dto.RestaurantCreateDto;
import com.example.tablereservation.dto.RestaurantDto;
import com.example.tablereservation.model.Restaurant;
import org.springframework.stereotype.Component;

@Component
public class RestaurantMapper {

    public RestaurantDto toDto(Restaurant restaurant) {
        if (restaurant == null) {
            return null;
        }

        RestaurantDto dto = new RestaurantDto();
        dto.setId(restaurant.getId());
        dto.setName(restaurant.getName());
        dto.setAddress(restaurant.getAddress());
        dto.setPhoneNumber(restaurant.getPhoneNumber());
        dto.setCuisineType(restaurant.getCuisineType());
        dto.setTableCount(restaurant.getTableCount());
        return dto;
    }

    public Restaurant toEntity(RestaurantCreateDto createDto) {
        if (createDto == null) {
            return null;
        }

        Restaurant restaurant = new Restaurant();
        restaurant.setName(createDto.getName());
        restaurant.setAddress(createDto.getAddress());
        restaurant.setPhoneNumber(createDto.getPhoneNumber());
        restaurant.setCuisineType(createDto.getCuisineType());
        restaurant.setTableCount(createDto.getTableCount());
        return restaurant;
    }

    public void updateEntity(RestaurantCreateDto createDto, Restaurant restaurant) {
        if (createDto == null || restaurant == null) {
            return;
        }

        restaurant.setName(createDto.getName());
        restaurant.setAddress(createDto.getAddress());
        restaurant.setPhoneNumber(createDto.getPhoneNumber());
        restaurant.setCuisineType(createDto.getCuisineType());
        restaurant.setTableCount(createDto.getTableCount());
    }
}