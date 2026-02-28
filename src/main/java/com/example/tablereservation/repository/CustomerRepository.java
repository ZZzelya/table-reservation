package com.example.tablereservation.repository;

import com.example.tablereservation.model.entity.Customer;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    Optional<Customer> findByEmail(String email);

    Optional<Customer> findByPhoneNumber(String phoneNumber);

    List<Customer> findByNameContainingIgnoreCase(String name);

    boolean existsByEmail(String email);

    @EntityGraph(attributePaths = {"reservations"})
    @Query("SELECT c FROM Customer c WHERE c.id = :id")
    Optional<Customer> findByIdWithReservations(@Param("id") Long id);
}