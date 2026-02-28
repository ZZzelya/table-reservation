package com.example.tablereservation.controller;

import com.example.tablereservation.dto.request.ReservationCreateDto;
import com.example.tablereservation.dto.request.RestaurantWithTablesAndMenuDto;
import com.example.tablereservation.dto.response.ReservationDto;
import com.example.tablereservation.dto.response.RestaurantDto;
import com.example.tablereservation.model.enums.ReservationStatus;
import com.example.tablereservation.service.ReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/reservations")
@RequiredArgsConstructor
public class ReservationController {

    private final ReservationService reservationService;

    @PostMapping
    public ResponseEntity<ReservationDto> createReservation(@RequestBody ReservationCreateDto createDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(reservationService.createReservation(createDto));
    }

    @PostMapping("/demo/without-tx")
    public ResponseEntity<Map<String, Object>> createReservationWithoutTransaction(@RequestBody
                                                                                   ReservationCreateDto createDto) {
        try {
            ReservationDto created = reservationService.createReservationWithoutTransaction(createDto);
            Map<String, Object> response = new HashMap<>();
            response.put("success", true);
            response.put("data", created);
            response.put("message", "Reservation created partially");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("success", false);
            errorResponse.put("error", e.getMessage());
            errorResponse.put("message", "Partial data might have been saved");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }

    @PostMapping("/demo/with-tx")
    public ResponseEntity<Map<String, Object>> createReservationWithTransaction(@RequestBody
                                                                                ReservationCreateDto createDto) {
        try {
            ReservationDto created = reservationService.createReservationWithTransaction(createDto);
            Map<String, Object> response = new HashMap<>();
            response.put("success", true);
            response.put("data", created);
            response.put("message", "Reservation created successfully with transaction");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("success", false);
            errorResponse.put("error", e.getMessage());
            errorResponse.put("message", "All changes rolled back");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }

    @PostMapping("/restaurant-with-tables")
    public ResponseEntity<RestaurantDto> createRestaurantWithTablesAndMenu(
        @RequestBody RestaurantWithTablesAndMenuDto createDto) {
        RestaurantDto result = reservationService.createRestaurantWithTablesAndMenu(
            createDto.getRestaurant(),
            createDto.getTables(),
            createDto.getMenuItems()
        );
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

    @GetMapping("/customer/{customerId}")
    public ResponseEntity<List<ReservationDto>> getReservationsByCustomer(@PathVariable Long customerId) {
        return ResponseEntity.ok(reservationService.getReservationsByCustomer(customerId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReservationDto> getReservationById(@PathVariable Long id) {
        return ResponseEntity.ok(reservationService.getReservationById(id));
    }

    @GetMapping("/{id}/with-details")
    public ResponseEntity<ReservationDto> getReservationWithDetails(@PathVariable Long id) {
        return ResponseEntity.ok(reservationService.getReservationWithDetails(id));
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<ReservationDto> updateReservationStatus(
        @PathVariable Long id,
        @RequestParam ReservationStatus status) {
        return ResponseEntity.ok(reservationService.updateReservationStatus(id, status));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReservation(@PathVariable Long id) {
        reservationService.deleteReservation(id);
        return ResponseEntity.noContent().build();
    }
}