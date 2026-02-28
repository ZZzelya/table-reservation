package com.example.tablereservation.dto.response;

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
public class ReservationDto {
    private Long id;
    private Long customerId;
    private String customerName;
    private Long tableId;
    private Integer tableNumber;
    private LocalDateTime reservationTime;
    private Integer partySize;
    private String specialRequests;
    private String status;
    private String notes;
    private List<MenuItemDto> preOrderedItems;
}