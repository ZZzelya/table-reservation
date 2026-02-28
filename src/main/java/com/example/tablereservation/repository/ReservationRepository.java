package com.example.tablereservation.repository;

import com.example.tablereservation.model.entity.Reservation;
import com.example.tablereservation.model.enums.ReservationStatus;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    List<Reservation> findByCustomerId(Long customerId);

    @Query("SELECT r FROM Reservation r WHERE r.table.id = :tableId AND r.reservationTime BETWEEN :start AND :end")
    List<Reservation> findByTableIdAndTimeRange(@Param("tableId") Long tableId, @Param("start") LocalDateTime start,
                                                @Param("end") LocalDateTime end);

    @EntityGraph(attributePaths = {"customer", "table", "preOrderedItems"})
    @Query("SELECT r FROM Reservation r WHERE r.status = :status")
    List<Reservation> findAllByStatusWithDetails(@Param("status") ReservationStatus status);

    @Query("SELECT r FROM Reservation r WHERE r.table.restaurant.id = :restaurantId AND DATE(r.reservationTime) =" +
        " DATE(:date)")
    List<Reservation> findByRestaurantAndDate(@Param("restaurantId") Long restaurantId,
                                              @Param("date") LocalDateTime date);

    long countByStatus(ReservationStatus status);
}