package com.example.tablereservation.controller;

import com.example.tablereservation.dto.request.RestaurantCreateDto;
import com.example.tablereservation.dto.response.RestaurantDto;
import com.example.tablereservation.dto.response.RestaurantWithTablesDto;
import com.example.tablereservation.service.RestaurantService;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/restaurants")
@RequiredArgsConstructor
public class RestaurantController {

    private final RestaurantService restaurantService;

    @GetMapping
    public ResponseEntity<List<RestaurantDto>> getAllRestaurants() {
        return ResponseEntity.ok(restaurantService.getAllRestaurants());
    }

    @GetMapping("/{id}")
    public ResponseEntity<RestaurantDto> getRestaurantById(@PathVariable Long id) {
        return ResponseEntity.ok(restaurantService.getRestaurantById(id));
    }

    @GetMapping("/{id}/with-tables")
    public ResponseEntity<RestaurantWithTablesDto> getRestaurantWithTables(@PathVariable Long id) {
        return ResponseEntity.ok(restaurantService.getRestaurantWithTables(id));
    }

    @GetMapping("/by-cuisine")
    public ResponseEntity<List<RestaurantDto>> getRestaurantsByCuisine(@RequestParam String cuisine) {
        return ResponseEntity.ok(restaurantService.getRestaurantsByCuisine(cuisine));
    }

    @PostMapping
    public ResponseEntity<RestaurantDto> createRestaurant(@RequestBody RestaurantCreateDto createDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(restaurantService.createRestaurant(createDto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<RestaurantDto> updateRestaurant(@PathVariable Long id,
                                                          @RequestBody RestaurantCreateDto updateDto) {
        return ResponseEntity.ok(restaurantService.updateRestaurant(id, updateDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRestaurant(@PathVariable Long id) {
        restaurantService.deleteRestaurant(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/demo/nplus1")
    public ResponseEntity<String> demonstrateNPlusOne(@RequestParam String cuisine) {
        restaurantService.demonstrateNPlusOneProblem(cuisine);
        return ResponseEntity.ok("N+1 problem demonstrated. Check logs!");
    }

    @GetMapping("/demo/solved")
    public ResponseEntity<String> solveNPlusOne(@RequestParam String cuisine) {
        restaurantService.solveNPlusOneWithFetchJoin(cuisine);
        return ResponseEntity.ok("N+1 solved with fetch join. Check logs!");
    }
}