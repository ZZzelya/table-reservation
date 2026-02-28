package com.example.tablereservation.controller;

import com.example.tablereservation.dto.request.MenuItemCreateDto;
import com.example.tablereservation.dto.response.MenuItemDto;
import com.example.tablereservation.service.MenuItemService;
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
@RequestMapping("/api/menu-items")
@RequiredArgsConstructor
public class MenuItemController {

    private final MenuItemService menuItemService;

    @PostMapping
    public ResponseEntity<MenuItemDto> createMenuItem(@RequestBody MenuItemCreateDto createDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(menuItemService.createMenuItem(createDto));
    }

    @GetMapping("/restaurant/{restaurantId}")
    public ResponseEntity<List<MenuItemDto>> getMenuItemsByRestaurant(@PathVariable Long restaurantId) {
        return ResponseEntity.ok(menuItemService.getMenuItemsByRestaurant(restaurantId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<MenuItemDto> getMenuItemById(@PathVariable Long id) {
        return ResponseEntity.ok(menuItemService.getMenuItemById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<MenuItemDto> updateMenuItem(@PathVariable Long id, @RequestBody MenuItemCreateDto updateDto) {
        return ResponseEntity.ok(menuItemService.updateMenuItem(id, updateDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMenuItem(@PathVariable Long id) {
        menuItemService.deleteMenuItem(id);
        return ResponseEntity.noContent().build();
    }
}