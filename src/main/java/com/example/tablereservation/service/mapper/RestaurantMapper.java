package com.example.tablereservation.service.mapper;

import com.example.tablereservation.dto.request.RestaurantCreateDto;
import com.example.tablereservation.dto.response.RestaurantDto;
import com.example.tablereservation.dto.response.RestaurantWithTablesDto;
import com.example.tablereservation.model.entity.Restaurant;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class RestaurantMapper {

    private final RestaurantTableMapper tableMapper;

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
        dto.setOpeningTime(restaurant.getOpeningTime());
        dto.setClosingTime(restaurant.getClosingTime());
        dto.setIsActive(restaurant.getIsActive());
        return dto;
    }

    public RestaurantWithTablesDto toDtoWithTables(Restaurant restaurant) {
        if (restaurant == null) {
            return null;
        }
        RestaurantWithTablesDto dto = new RestaurantWithTablesDto();
        dto.setId(restaurant.getId());
        dto.setName(restaurant.getName());
        dto.setAddress(restaurant.getAddress());
        dto.setPhoneNumber(restaurant.getPhoneNumber());
        dto.setCuisineType(restaurant.getCuisineType());
        dto.setTableCount(restaurant.getTableCount());
        dto.setOpeningTime(restaurant.getOpeningTime());
        dto.setClosingTime(restaurant.getClosingTime());
        dto.setIsActive(restaurant.getIsActive());
        dto.setTables(tableMapper.toDtoList(restaurant.getTables()));
        return dto;
    }

    public Restaurant toEntity(RestaurantCreateDto dto) {
        if (dto == null) {
            return null;
        }
        Restaurant restaurant = new Restaurant();
        restaurant.setName(dto.getName());
        restaurant.setAddress(dto.getAddress());
        restaurant.setPhoneNumber(dto.getPhoneNumber());
        restaurant.setCuisineType(dto.getCuisineType());
        restaurant.setTableCount(dto.getTableCount());
        restaurant.setOpeningTime(dto.getOpeningTime());
        restaurant.setClosingTime(dto.getClosingTime());
        restaurant.setIsActive(true);
        return restaurant;
    }

    public void updateEntity(Restaurant restaurant, RestaurantCreateDto dto) {
        if (dto == null) {
            return;
        }
        restaurant.setName(dto.getName());
        restaurant.setAddress(dto.getAddress());
        restaurant.setPhoneNumber(dto.getPhoneNumber());
        restaurant.setCuisineType(dto.getCuisineType());
        restaurant.setTableCount(dto.getTableCount());
        restaurant.setOpeningTime(dto.getOpeningTime());
        restaurant.setClosingTime(dto.getClosingTime());
    }

    public List<RestaurantDto> toDtoList(List<Restaurant> restaurants) {
        return restaurants.stream().map(this::toDto).toList();
    }
}