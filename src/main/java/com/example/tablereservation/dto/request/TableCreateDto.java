package com.example.tablereservation.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TableCreateDto {
    private Long restaurantId;
    private Integer tableNumber;
    private Integer capacity;
    private Boolean isAvailable;
    private String location;
}