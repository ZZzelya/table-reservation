package com.example.tablereservation.service.mapper;

import com.example.tablereservation.dto.CustomerWithReservationsDto;
import com.example.tablereservation.dto.request.CustomerCreateDto;
import com.example.tablereservation.dto.response.CustomerDto;
import com.example.tablereservation.model.entity.Customer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class CustomerMapper {

    private final ReservationMapper reservationMapper;

    public CustomerDto toDto(Customer customer) {
        if (customer == null) {
            return null;
        }
        CustomerDto dto = new CustomerDto();
        dto.setId(customer.getId());
        dto.setName(customer.getName());
        dto.setEmail(customer.getEmail());
        dto.setPhoneNumber(customer.getPhoneNumber());
        dto.setPreferredCuisine(customer.getPreferredCuisine());
        dto.setLoyaltyPoints(customer.getLoyaltyPoints());
        dto.setIsActive(customer.getIsActive());
        return dto;
    }

    public CustomerWithReservationsDto toDtoWithReservations(Customer customer) {
        if (customer == null) {
            return null;
        }
        CustomerWithReservationsDto dto = new CustomerWithReservationsDto();
        dto.setId(customer.getId());
        dto.setName(customer.getName());
        dto.setEmail(customer.getEmail());
        dto.setPhoneNumber(customer.getPhoneNumber());
        dto.setPreferredCuisine(customer.getPreferredCuisine());
        dto.setLoyaltyPoints(customer.getLoyaltyPoints());
        dto.setIsActive(customer.getIsActive());
        dto.setReservations(reservationMapper.toDtoList(customer.getReservations()));
        return dto;
    }

    public Customer toEntity(CustomerCreateDto dto) {
        if (dto == null) {
            return null;
        }
        Customer customer = new Customer();
        customer.setName(dto.getName());
        customer.setEmail(dto.getEmail());
        customer.setPhoneNumber(dto.getPhoneNumber());
        customer.setPreferredCuisine(dto.getPreferredCuisine());
        customer.setLoyaltyPoints(0);
        customer.setIsActive(true);
        return customer;
    }

    public void updateEntity(Customer customer, CustomerCreateDto dto) {
        if (dto == null) {
            return;
        }
        customer.setName(dto.getName());
        customer.setEmail(dto.getEmail());
        customer.setPhoneNumber(dto.getPhoneNumber());
        customer.setPreferredCuisine(dto.getPreferredCuisine());
    }

    public List<CustomerDto> toDtoList(List<Customer> customers) {
        return customers.stream().map(this::toDto).toList();
    }
}