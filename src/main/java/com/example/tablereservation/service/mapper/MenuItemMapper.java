package com.example.tablereservation.service.mapper;

import com.example.tablereservation.dto.request.MenuItemCreateDto;
import com.example.tablereservation.dto.response.MenuItemDto;
import com.example.tablereservation.model.entity.MenuItem;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MenuItemMapper {

    public MenuItemDto toDto(MenuItem menuItem) {
        if (menuItem == null) {
            return null;
        }
        MenuItemDto dto = new MenuItemDto();
        dto.setId(menuItem.getId());
        dto.setRestaurantId(menuItem.getRestaurant().getId());
        dto.setName(menuItem.getName());
        dto.setDescription(menuItem.getDescription());
        dto.setPrice(menuItem.getPrice());
        dto.setCategory(menuItem.getCategory());
        dto.setIsAvailable(menuItem.getIsAvailable());
        dto.setPreparationTime(menuItem.getPreparationTime());
        dto.setImageUrl(menuItem.getImageUrl());
        return dto;
    }

    public MenuItem toEntity(MenuItemCreateDto dto) {
        if (dto == null) {
            return null;
        }
        MenuItem menuItem = new MenuItem();
        menuItem.setName(dto.getName());
        menuItem.setDescription(dto.getDescription());
        menuItem.setPrice(dto.getPrice());
        menuItem.setCategory(dto.getCategory());
        menuItem.setIsAvailable(dto.getIsAvailable() != null ? dto.getIsAvailable() : Boolean.TRUE);
        menuItem.setPreparationTime(dto.getPreparationTime());
        menuItem.setImageUrl(dto.getImageUrl());
        return menuItem;
    }

    public void updateEntity(MenuItem menuItem, MenuItemCreateDto dto) {
        if (dto == null) {
            return;
        }
        menuItem.setName(dto.getName());
        menuItem.setDescription(dto.getDescription());
        menuItem.setPrice(dto.getPrice());
        menuItem.setCategory(dto.getCategory());
        menuItem.setIsAvailable(dto.getIsAvailable());
        menuItem.setPreparationTime(dto.getPreparationTime());
        menuItem.setImageUrl(dto.getImageUrl());
    }

    public List<MenuItemDto> toDtoList(List<MenuItem> menuItems) {
        return menuItems.stream().map(this::toDto).toList();
    }
}