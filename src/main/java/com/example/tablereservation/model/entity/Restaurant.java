package com.example.tablereservation.model.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
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
@Table(name = "restaurants")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(exclude = {"tables", "menuItems"})
@ToString(exclude = {"tables", "menuItems"})
public class Restaurant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String name;

    @Column(length = 200)
    private String address;

    @Column(name = "phone_number", length = 20)
    private String phoneNumber;

    @Column(name = "cuisine_type", length = 50)
    private String cuisineType;

    @Column(name = "table_count")
    private Integer tableCount;

    @Column(name = "opening_time")
    private String openingTime;

    @Column(name = "closing_time")
    private String closingTime;

    @Column(name = "is_active")
    @Builder.Default
    private Boolean isActive = true;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @OneToMany(mappedBy = "restaurant", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    @Builder.Default
    private List<RestaurantTable> tables = new ArrayList<>();

    @OneToMany(mappedBy = "restaurant", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    @Builder.Default
    private List<MenuItem> menuItems = new ArrayList<>();

    public void addTable(RestaurantTable table) {
        tables.add(table);
        table.setRestaurant(this);
    }

    public void removeTable(RestaurantTable table) {
        tables.remove(table);
        table.setRestaurant(null);
    }

    public void addMenuItem(MenuItem menuItem) {
        menuItems.add(menuItem);
        menuItem.setRestaurant(this);
    }

    public void removeMenuItem(MenuItem menuItem) {
        menuItems.remove(menuItem);
        menuItem.setRestaurant(null);
    }
}