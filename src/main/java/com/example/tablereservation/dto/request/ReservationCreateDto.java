package com.example.tablereservation.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReservationCreateDto {
    private Long customerId;
    private Long tableId;
    private LocalDateTime reservationTime;
    private Integer partySize;
    private String specialRequests;
    private String notes;
    private List<Long> menuItemIds;
}