package com.example.tablereservation.controller;

import com.example.tablereservation.dto.CustomerWithReservationsDto;
import com.example.tablereservation.dto.request.CustomerCreateDto;
import com.example.tablereservation.dto.response.CustomerDto;
import com.example.tablereservation.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/customers")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    @PostMapping
    public ResponseEntity<CustomerDto> createCustomer(@RequestBody CustomerCreateDto createDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(customerService.createCustomer(createDto));
    }

    @GetMapping
    public ResponseEntity<List<CustomerDto>> getAllCustomers() {
        return ResponseEntity.ok(customerService.getAllCustomers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerDto> getCustomerById(@PathVariable Long id) {
        return ResponseEntity.ok(customerService.getCustomerById(id));
    }

    @GetMapping("/{id}/with-reservations")
    public ResponseEntity<CustomerWithReservationsDto> getCustomerWithReservations(@PathVariable Long id) {
        return ResponseEntity.ok(customerService.getCustomerWithReservations(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CustomerDto> updateCustomer(@PathVariable Long id, @RequestBody CustomerCreateDto updateDto) {
        return ResponseEntity.ok(customerService.updateCustomer(id, updateDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable Long id) {
        customerService.deleteCustomer(id);
        return ResponseEntity.noContent().build();
    }
}