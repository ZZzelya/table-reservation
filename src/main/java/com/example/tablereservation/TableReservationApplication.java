package com.example.tablereservation;

import com.example.tablereservation.repository.RestaurantRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class TableReservationApplication {

    public static void main(String[] args) {
        SpringApplication.run(TableReservationApplication.class, args);
    }

    @Bean
    public CommandLineRunner demoData(RestaurantRepository repository) {
        return args -> {
            repository.init();
            System.out.println("Тестовые данные загружены!");
        };
    }
}