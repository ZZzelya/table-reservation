package com.example.tablereservation.model;

import lombok.Data;

@Data

public class Restaurant {
    private Long id;
    private String name;
    private String address;
    private String phoneNumber;
    private String cuisineType;
    private Integer tableCount;
}
