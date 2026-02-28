package com.example.tablereservation.repository;

import com.example.tablereservation.model.entity.MenuItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenuItemRepository extends JpaRepository<MenuItem, Long> {
    List<MenuItem> findByRestaurantId(Long restaurantId);

    @Query("SELECT m FROM MenuItem m WHERE m.restaurant.id = :restaurantId AND m.isAvailable = true")
    List<MenuItem> findAvailableItemsByRestaurant(@Param("restaurantId") Long restaurantId);

    List<MenuItem> findByRestaurantIdAndCategory(Long restaurantId, String category);

    List<MenuItem> findByNameContainingIgnoreCase(String name);
}