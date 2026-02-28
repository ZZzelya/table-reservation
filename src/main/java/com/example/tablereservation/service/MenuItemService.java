package com.example.tablereservation.service;

import com.example.tablereservation.dto.request.MenuItemCreateDto;
import com.example.tablereservation.dto.response.MenuItemDto;
import com.example.tablereservation.exception.ResourceNotFoundException;
import com.example.tablereservation.model.entity.MenuItem;
import com.example.tablereservation.model.entity.Restaurant;
import com.example.tablereservation.repository.MenuItemRepository;
import com.example.tablereservation.repository.RestaurantRepository;
import com.example.tablereservation.service.mapper.MenuItemMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class MenuItemService {

    private final MenuItemRepository menuItemRepository;
    private final RestaurantRepository restaurantRepository;
    private final MenuItemMapper menuItemMapper;

    @Transactional(readOnly = true)
    public List<MenuItemDto> getMenuItemsByRestaurant(Long restaurantId) {
        return menuItemMapper.toDtoList(menuItemRepository.findByRestaurantId(restaurantId));
    }

    @Transactional(readOnly = true)
    public MenuItemDto getMenuItemById(Long id) {
        MenuItem menuItem = findMenuItemOrThrow(id);
        return menuItemMapper.toDto(menuItem);
    }

    @Transactional
    public MenuItemDto createMenuItem(MenuItemCreateDto createDto) {
        Restaurant restaurant = restaurantRepository.findById(createDto.getRestaurantId())
            .orElseThrow(() -> new ResourceNotFoundException("Restaurant not found with id: "
                + createDto.getRestaurantId()));

        MenuItem menuItem = menuItemMapper.toEntity(createDto);
        menuItem.setRestaurant(restaurant);
        MenuItem savedMenuItem = menuItemRepository.save(menuItem);
        return menuItemMapper.toDto(savedMenuItem);
    }

    @Transactional
    public MenuItemDto updateMenuItem(Long id, MenuItemCreateDto updateDto) {
        MenuItem menuItem = findMenuItemOrThrow(id);
        menuItemMapper.updateEntity(menuItem, updateDto);
        MenuItem updatedMenuItem = menuItemRepository.save(menuItem);
        return menuItemMapper.toDto(updatedMenuItem);
    }

    @Transactional
    public void deleteMenuItem(Long id) {
        MenuItem menuItem = findMenuItemOrThrow(id);
        menuItemRepository.delete(menuItem);
    }

    private MenuItem findMenuItemOrThrow(Long id) {
        return menuItemRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Menu item not found with id: " + id));
    }
}