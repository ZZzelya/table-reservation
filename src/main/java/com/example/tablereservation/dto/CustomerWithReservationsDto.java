package com.example.tablereservation.dto;

import com.example.tablereservation.dto.response.ReservationDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CustomerWithReservationsDto {
    private Long id;
    private String name;
    private String email;
    private String phoneNumber;
    private String preferredCuisine;
    private Integer loyaltyPoints;
    private Boolean isActive;
    private List<ReservationDto> reservations;
}