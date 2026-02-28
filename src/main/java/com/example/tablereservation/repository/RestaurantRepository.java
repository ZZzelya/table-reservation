package com.example.tablereservation.repository;

import com.example.tablereservation.model.entity.Restaurant;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {
    List<Restaurant> findByCuisineTypeIgnoreCase(String cuisineType);

    List<Restaurant> findByNameContainingIgnoreCase(String name);

    List<Restaurant> findByIsActiveTrue();

    Optional<Restaurant> findByPhoneNumber(String phoneNumber);

    boolean existsByPhoneNumber(String phoneNumber);

    @EntityGraph(attributePaths = {"tables"})
    @Query("SELECT r FROM Restaurant r WHERE r.id = :id")
    Optional<Restaurant> findByIdWithTables(@Param("id") Long id);

    @EntityGraph(attributePaths = {"menuItems"})
    @Query("SELECT r FROM Restaurant r WHERE r.id = :id")
    Optional<Restaurant> findByIdWithMenuItems(@Param("id") Long id);

    @Query("SELECT DISTINCT r FROM Restaurant r LEFT JOIN FETCH r.tables WHERE r.cuisineType = :cuisineType")
    List<Restaurant> findByCuisineTypeWithTables(@Param("cuisineType") String cuisineType);
}