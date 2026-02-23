package com.example.tablereservation.dto;

import lombok.Data;

// Этот объект мы могли бы принимать от клиента, например, при создании нового ресторана
@Data
public class RestaurantCreateDto {
    private String name;
    private String address;
    private String phoneNumber;
    private String cuisineType;
    private Integer tableCount;
}