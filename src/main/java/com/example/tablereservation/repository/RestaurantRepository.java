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

    public void init() {
        Restaurant r1 = new Restaurant();
        r1.setName("Кухмистр");
        r1.setAddress("Минск, ул. Карла Маркса, 40");
        r1.setPhoneNumber("+375 17 123-45-67");
        r1.setCuisineType("Белорусская");
        r1.setTableCount(25);
        save(r1);

        Restaurant r2 = new Restaurant();
        r2.setName("Лидо");
        r2.setAddress("Минск, пр-т Независимости, 49");
        r2.setPhoneNumber("+375 29 777-88-99");
        r2.setCuisineType("Европейская");
        r2.setTableCount(40);
        save(r2);

        Restaurant r3 = new Restaurant();
        r3.setName("Васильки");
        r3.setAddress("Минск, ул. Немига, 8");
        r3.setPhoneNumber("+375 44 555-66-77");
        r3.setCuisineType("Белорусская");
        r3.setTableCount(50);
        save(r3);

        Restaurant r4 = new Restaurant();
        r4.setName("Талака");
        r4.setAddress("Минск, ул. Притыцкого, 34");
        r4.setPhoneNumber("+375 25 333-44-55");
        r4.setCuisineType("Белорусская");
        r4.setTableCount(35);
        save(r4);

        Restaurant r5 = new Restaurant();
        r5.setName("Грузинский дворик");
        r5.setAddress("Минск, ул. Сурганова, 57Б");
        r5.setPhoneNumber("+375 33 222-11-00");
        r5.setCuisineType("Грузинская");
        r5.setTableCount(20);
        save(r5);
    }
}