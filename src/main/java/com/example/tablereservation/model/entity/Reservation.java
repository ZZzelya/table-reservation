package com.example.tablereservation.model.entity;

import com.example.tablereservation.model.enums.ReservationStatus;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "reservations")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(exclude = {"customer", "table", "preOrderedItems"})
@ToString(exclude = {"customer", "table", "preOrderedItems"})
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "reservation_time", nullable = false)
    private LocalDateTime reservationTime;

    @Column(name = "party_size", nullable = false)
    private Integer partySize;

    @Column(name = "special_requests", length = 500)
    private String specialRequests;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    @Builder.Default
    private ReservationStatus status = ReservationStatus.PENDING;

    @Column(length = 500)
    private String notes;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "table_id", nullable = false)
    private RestaurantTable table;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
        name = "reservation_menu_items",
        joinColumns = @JoinColumn(name = "reservation_id"),
        inverseJoinColumns = @JoinColumn(name = "menu_item_id")
    )
    @Builder.Default
    private List<MenuItem> preOrderedItems = new ArrayList<>();
}