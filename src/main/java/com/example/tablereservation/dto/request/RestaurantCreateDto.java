package com.example.tablereservation.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RestaurantCreateDto {
    private String name;
    private String address;
    private String phoneNumber;
    private String cuisineType;
    private Integer tableCount;
    private String openingTime;
    private String closingTime;
}