package com.example.tablereservation.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RestaurantWithTablesAndMenuDto {
    private RestaurantCreateDto restaurant;
    private List<TableCreateDto> tables;
    private List<MenuItemCreateDto> menuItems;
}