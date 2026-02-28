package com.example.tablereservation.repository;

import com.example.tablereservation.model.entity.RestaurantTable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TableRepository extends JpaRepository<RestaurantTable, Long> {
    List<RestaurantTable> findByRestaurantId(Long restaurantId);

    @Query("SELECT t FROM RestaurantTable t WHERE t.restaurant.id = :restaurantId AND t.isAvailable = true")
    List<RestaurantTable> findAvailableTablesByRestaurant(@Param("restaurantId") Long restaurantId);

    @Query("SELECT t FROM RestaurantTable t WHERE t.restaurant.id = :restaurantId AND t.capacity >= :capacity")
    List<RestaurantTable> findByRestaurantIdAndMinCapacity(@Param("restaurantId") Long restaurantId,
                                                           @Param("capacity") Integer capacity);

    boolean existsByRestaurantIdAndTableNumber(Long restaurantId, Integer tableNumber);
}