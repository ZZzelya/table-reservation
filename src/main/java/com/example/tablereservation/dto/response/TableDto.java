package com.example.tablereservation.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TableDto {
    private Long id;
    private Long restaurantId;
    private Integer tableNumber;
    private Integer capacity;
    private Boolean isAvailable;
    private String location;
}