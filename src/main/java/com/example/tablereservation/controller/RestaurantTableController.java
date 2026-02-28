package com.example.tablereservation.controller;

import com.example.tablereservation.dto.request.TableCreateDto;
import com.example.tablereservation.dto.response.TableDto;
import com.example.tablereservation.service.RestaurantTableService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/tables")
@RequiredArgsConstructor
public class RestaurantTableController {

    private final RestaurantTableService tableService;

    @PostMapping
    public ResponseEntity<TableDto> createTable(@RequestBody TableCreateDto createDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(tableService.createTable(createDto));
    }

    @GetMapping("/restaurant/{restaurantId}")
    public ResponseEntity<List<TableDto>> getTablesByRestaurant(@PathVariable Long restaurantId) {
        return ResponseEntity.ok(tableService.getTablesByRestaurant(restaurantId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<TableDto> getTableById(@PathVariable Long id) {
        return ResponseEntity.ok(tableService.getTableById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TableDto> updateTable(@PathVariable Long id, @RequestBody TableCreateDto updateDto) {
        return ResponseEntity.ok(tableService.updateTable(id, updateDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTable(@PathVariable Long id) {
        tableService.deleteTable(id);
        return ResponseEntity.noContent().build();
    }
}