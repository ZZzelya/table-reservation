package com.example.tablereservation.service.mapper;

import com.example.tablereservation.dto.request.TableCreateDto;
import com.example.tablereservation.dto.response.TableDto;
import com.example.tablereservation.model.entity.RestaurantTable;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RestaurantTableMapper {

    public TableDto toDto(RestaurantTable table) {
        if (table == null) {
            return null;
        }
        TableDto dto = new TableDto();
        dto.setId(table.getId());
        dto.setRestaurantId(table.getRestaurant().getId());
        dto.setTableNumber(table.getTableNumber());
        dto.setCapacity(table.getCapacity());
        dto.setIsAvailable(table.getIsAvailable());
        dto.setLocation(table.getLocation());
        return dto;
    }

    public RestaurantTable toEntity(TableCreateDto dto) {
        if (dto == null) {
            return null;
        }
        RestaurantTable table = new RestaurantTable();
        table.setTableNumber(dto.getTableNumber());
        table.setCapacity(dto.getCapacity());
        table.setIsAvailable(dto.getIsAvailable() != null ? dto.getIsAvailable() : Boolean.TRUE);
        table.setLocation(dto.getLocation());
        return table;
    }

    public void updateEntity(RestaurantTable table, TableCreateDto dto) {
        if (dto == null) {
            return;
        }
        table.setTableNumber(dto.getTableNumber());
        table.setCapacity(dto.getCapacity());
        table.setIsAvailable(dto.getIsAvailable());
        table.setLocation(dto.getLocation());
    }

    public List<TableDto> toDtoList(List<RestaurantTable> tables) {
        return tables.stream().map(this::toDto).toList();
    }
}