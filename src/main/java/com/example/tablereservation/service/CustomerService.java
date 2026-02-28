package com.example.tablereservation.service;

import com.example.tablereservation.dto.CustomerWithReservationsDto;
import com.example.tablereservation.dto.request.CustomerCreateDto;
import com.example.tablereservation.dto.response.CustomerDto;
import com.example.tablereservation.exception.ResourceNotFoundException;
import com.example.tablereservation.model.entity.Customer;
import com.example.tablereservation.repository.CustomerRepository;
import com.example.tablereservation.service.mapper.CustomerMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;

    @Transactional(readOnly = true)
    public List<CustomerDto> getAllCustomers() {
        return customerMapper.toDtoList(customerRepository.findAll());
    }

    @Transactional(readOnly = true)
    public CustomerDto getCustomerById(Long id) {
        Customer customer = findCustomerOrThrow(id);
        return customerMapper.toDto(customer);
    }

    @Transactional(readOnly = true)
    public CustomerWithReservationsDto getCustomerWithReservations(Long id) {
        Customer customer = customerRepository.findByIdWithReservations(id)
            .orElseThrow(() -> new ResourceNotFoundException("Customer not found with id: " + id));
        return customerMapper.toDtoWithReservations(customer);
    }

    @Transactional
    public CustomerDto createCustomer(CustomerCreateDto createDto) {
        if (customerRepository.existsByEmail(createDto.getEmail())) {
            throw new IllegalArgumentException("Customer with this email already exists");
        }
        Customer customer = customerMapper.toEntity(createDto);
        Customer savedCustomer = customerRepository.save(customer);
        return customerMapper.toDto(savedCustomer);
    }

    @Transactional
    public CustomerDto updateCustomer(Long id, CustomerCreateDto updateDto) {
        Customer customer = findCustomerOrThrow(id);
        customerMapper.updateEntity(customer, updateDto);
        Customer updatedCustomer = customerRepository.save(customer);
        return customerMapper.toDto(updatedCustomer);
    }

    @Transactional
    public void deleteCustomer(Long id) {
        Customer customer = findCustomerOrThrow(id);
        customerRepository.delete(customer);
    }

    private Customer findCustomerOrThrow(Long id) {
        return customerRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Customer not found with id: " + id));
    }
}