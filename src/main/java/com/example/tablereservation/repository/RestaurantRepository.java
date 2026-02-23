package com.example.tablereservation.repository;

import com.example.tablereservation.model.Restaurant;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class RestaurantRepository {

    // Потокобезопасное хранилище
    private final ConcurrentHashMap<Long, Restaurant> storage = new ConcurrentHashMap<>();
    private final AtomicLong idGenerator = new AtomicLong(1);

    public Restaurant save(Restaurant restaurant) {
        if (restaurant.getId() == null) {
            restaurant.setId(idGenerator.getAndIncrement());
        }
        storage.put(restaurant.getId(), restaurant);
        return restaurant;
    }

    public Optional<Restaurant> findById(Long id) {
        return Optional.ofNullable(storage.get(id));
    }

    public List<Restaurant> findAll() {
        return new ArrayList<>(storage.values());
    }
}