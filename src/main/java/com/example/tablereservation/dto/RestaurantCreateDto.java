package com.example.tablereservation.dto;

import lombok.Data;

@Data
public class RestaurantCreateDto {
    private String name;
    private String address;
    private String phoneNumber;
    private String cuisineType;
    private Integer tableCount;
}