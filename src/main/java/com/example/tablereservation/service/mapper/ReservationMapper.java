package com.example.tablereservation.service.mapper;

import com.example.tablereservation.dto.request.ReservationCreateDto;
import com.example.tablereservation.dto.response.ReservationDto;
import com.example.tablereservation.model.entity.Reservation;
import com.example.tablereservation.model.enums.ReservationStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ReservationMapper {

    private final MenuItemMapper menuItemMapper;

    public ReservationDto toDto(Reservation reservation) {
        if (reservation == null) {
            return null;
        }
        ReservationDto dto = new ReservationDto();
        dto.setId(reservation.getId());
        dto.setCustomerId(reservation.getCustomer().getId());
        dto.setCustomerName(reservation.getCustomer().getName());
        dto.setTableId(reservation.getTable().getId());
        dto.setTableNumber(reservation.getTable().getTableNumber());
        dto.setReservationTime(reservation.getReservationTime());
        dto.setPartySize(reservation.getPartySize());
        dto.setSpecialRequests(reservation.getSpecialRequests());
        dto.setStatus(reservation.getStatus().name());
        dto.setNotes(reservation.getNotes());
        dto.setPreOrderedItems(menuItemMapper.toDtoList(reservation.getPreOrderedItems()));
        return dto;
    }

    public Reservation toEntity(ReservationCreateDto dto) {
        if (dto == null) {
            return null;
        }
        Reservation reservation = new Reservation();
        reservation.setReservationTime(dto.getReservationTime());
        reservation.setPartySize(dto.getPartySize());
        reservation.setSpecialRequests(dto.getSpecialRequests());
        reservation.setNotes(dto.getNotes());
        reservation.setStatus(ReservationStatus.PENDING);
        return reservation;
    }

    public void updateEntity(Reservation reservation, ReservationCreateDto dto) {
        if (dto == null) {
            return;
        }
        reservation.setReservationTime(dto.getReservationTime());
        reservation.setPartySize(dto.getPartySize());
        reservation.setSpecialRequests(dto.getSpecialRequests());
        reservation.setNotes(dto.getNotes());
    }

    public List<ReservationDto> toDtoList(List<Reservation> reservations) {
        return reservations.stream().map(this::toDto).toList();
    }
}