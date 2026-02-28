package com.example.tablereservation.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CustomerDto {
    private Long id;
    private String name;
    private String email;
    private String phoneNumber;
    private String preferredCuisine;
    private Integer loyaltyPoints;
    private Boolean isActive;
}