package com.example.tablereservation.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RestaurantWithTablesDto {
    private Long id;
    private String name;
    private String address;
    private String phoneNumber;
    private String cuisineType;
    private Integer tableCount;
    private String openingTime;
    private String closingTime;
    private Boolean isActive;
    private List<TableDto> tables;
}