package com.example.tablereservation.dto;

import lombok.Data;

// Этот объект мы будем возвращать в теле ответа на GET запросы
@Data
public class RestaurantDto {
    private Long id;
    private String name;
    private String address;
    private String phoneNumber;
    private String cuisineType;
    private Integer tableCount;
}