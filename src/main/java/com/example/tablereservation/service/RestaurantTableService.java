package com.example.tablereservation.service;

import com.example.tablereservation.dto.request.TableCreateDto;
import com.example.tablereservation.dto.response.TableDto;
import com.example.tablereservation.exception.ResourceNotFoundException;
import com.example.tablereservation.model.entity.Restaurant;
import com.example.tablereservation.model.entity.RestaurantTable;
import com.example.tablereservation.repository.RestaurantRepository;
import com.example.tablereservation.repository.TableRepository;
import com.example.tablereservation.service.mapper.RestaurantTableMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class RestaurantTableService {

    private final TableRepository tableRepository;
    private final RestaurantRepository restaurantRepository;
    private final RestaurantTableMapper tableMapper;

    @Transactional(readOnly = true)
    public List<TableDto> getTablesByRestaurant(Long restaurantId) {
        return tableMapper.toDtoList(tableRepository.findByRestaurantId(restaurantId));
    }

    @Transactional(readOnly = true)
    public TableDto getTableById(Long id) {
        RestaurantTable table = findTableOrThrow(id);
        return tableMapper.toDto(table);
    }

    @Transactional
    public TableDto createTable(TableCreateDto createDto) {
        Restaurant restaurant = restaurantRepository.findById(createDto.getRestaurantId())
            .orElseThrow(() -> new ResourceNotFoundException("Restaurant not found with id: "
                + createDto.getRestaurantId()));

        if (tableRepository.existsByRestaurantIdAndTableNumber(createDto.getRestaurantId(),
            createDto.getTableNumber())) {
            throw new IllegalArgumentException("Table number already exists in this restaurant");
        }

        RestaurantTable table = tableMapper.toEntity(createDto);
        table.setRestaurant(restaurant);
        RestaurantTable savedTable = tableRepository.save(table);
        return tableMapper.toDto(savedTable);
    }

    @Transactional
    public TableDto updateTable(Long id, TableCreateDto updateDto) {
        RestaurantTable table = findTableOrThrow(id);
        tableMapper.updateEntity(table, updateDto);
        RestaurantTable updatedTable = tableRepository.save(table);
        return tableMapper.toDto(updatedTable);
    }

    @Transactional
    public void deleteTable(Long id) {
        RestaurantTable table = findTableOrThrow(id);
        tableRepository.delete(table);
    }

    private RestaurantTable findTableOrThrow(Long id) {
        return tableRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Table not found with id: " + id));
    }
}