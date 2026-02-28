package com.example.tablereservation.service;

import com.example.tablereservation.dto.request.MenuItemCreateDto;
import com.example.tablereservation.dto.request.ReservationCreateDto;
import com.example.tablereservation.dto.request.RestaurantCreateDto;
import com.example.tablereservation.dto.request.TableCreateDto;
import com.example.tablereservation.dto.response.ReservationDto;
import com.example.tablereservation.dto.response.RestaurantDto;
import com.example.tablereservation.exception.BusinessException;
import com.example.tablereservation.exception.ResourceNotFoundException;
import com.example.tablereservation.model.entity.Customer;
import com.example.tablereservation.model.entity.MenuItem;
import com.example.tablereservation.model.entity.Reservation;
import com.example.tablereservation.model.entity.Restaurant;
import com.example.tablereservation.model.entity.RestaurantTable;
import com.example.tablereservation.model.enums.ReservationStatus;
import com.example.tablereservation.repository.CustomerRepository;
import com.example.tablereservation.repository.MenuItemRepository;
import com.example.tablereservation.repository.ReservationRepository;
import com.example.tablereservation.repository.RestaurantRepository;
import com.example.tablereservation.repository.TableRepository;
import com.example.tablereservation.service.mapper.ReservationMapper;
import com.example.tablereservation.service.mapper.RestaurantMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ReservationService {

    private final ReservationRepository reservationRepository;
    private final CustomerRepository customerRepository;
    private final TableRepository tableRepository;
    private final MenuItemRepository menuItemRepository;
    private final RestaurantRepository restaurantRepository;
    private final ReservationMapper reservationMapper;
    private final RestaurantMapper restaurantMapper;

    @Transactional(readOnly = true)
    public List<ReservationDto> getReservationsByCustomer(Long customerId) {
        return reservationRepository.findByCustomerId(customerId)
            .stream()
            .map(reservationMapper::toDto)
            .toList();
    }

    @Transactional(readOnly = true)
    public ReservationDto getReservationById(Long id) {
        Reservation reservation = findReservationOrThrow(id);
        return reservationMapper.toDto(reservation);
    }

    @Transactional(readOnly = true)
    public ReservationDto getReservationWithDetails(Long id) {
        Reservation reservation = findReservationOrThrow(id);
        reservation.getCustomer().getName();
        reservation.getTable().getTableNumber();
        reservation.getPreOrderedItems().size();
        return reservationMapper.toDto(reservation);
    }

    @Transactional
    public ReservationDto createReservation(ReservationCreateDto createDto) {
        Customer customer = customerRepository.findById(createDto.getCustomerId())
            .orElseThrow(() -> new ResourceNotFoundException("Customer not found with id: " +
                createDto.getCustomerId()));

        RestaurantTable table = tableRepository.findById(createDto.getTableId())
            .orElseThrow(() -> new ResourceNotFoundException("Table not found with id: " + createDto.getTableId()));

        if (!table.getIsAvailable()) {
            throw new IllegalStateException("Table is not available");
        }

        Reservation reservation = reservationMapper.toEntity(createDto);
        reservation.setCustomer(customer);
        reservation.setTable(table);

        if (createDto.getMenuItemIds() != null && !createDto.getMenuItemIds().isEmpty()) {
            List<MenuItem> menuItems = menuItemRepository.findAllById(createDto.getMenuItemIds());
            reservation.setPreOrderedItems(menuItems);
        }

        Reservation savedReservation = reservationRepository.save(reservation);
        table.setIsAvailable(false);
        tableRepository.save(table);

        return reservationMapper.toDto(savedReservation);
    }

    public ReservationDto createReservationWithoutTransaction(ReservationCreateDto createDto) {
        log.info("=== Creating reservation WITHOUT transaction (partial save demo) ===");

        Customer customer = customerRepository.findById(createDto.getCustomerId())
            .orElseThrow(() -> new ResourceNotFoundException("Customer not found"));

        RestaurantTable table = tableRepository.findById(createDto.getTableId())
            .orElseThrow(() -> new ResourceNotFoundException("Table not found"));

        Reservation reservation = reservationMapper.toEntity(createDto);
        reservation.setCustomer(customer);
        reservation.setTable(table);

        Reservation savedReservation = reservationRepository.save(reservation);
        log.info("Reservation saved with id: {}", savedReservation.getId());

        List<MenuItem> menuItems = menuItemRepository.findAllById(createDto.getMenuItemIds());

        if (menuItems.size() != createDto.getMenuItemIds().size()) {
            throw new BusinessException("Some menu items not found - reservation already saved!");
        }

        savedReservation.setPreOrderedItems(menuItems);

        return reservationMapper.toDto(savedReservation);
    }

    @Transactional
    public ReservationDto createReservationWithTransaction(ReservationCreateDto createDto) {
        log.info("=== Creating reservation WITH transaction (full rollback demo) ===");

        Customer customer = customerRepository.findById(createDto.getCustomerId())
            .orElseThrow(() -> new ResourceNotFoundException("Customer not found"));

        RestaurantTable table = tableRepository.findById(createDto.getTableId())
            .orElseThrow(() -> new ResourceNotFoundException("Table not found"));

        if (!table.getIsAvailable()) {
            throw new IllegalStateException("Table not available");
        }

        Reservation reservation = reservationMapper.toEntity(createDto);
        reservation.setCustomer(customer);
        reservation.setTable(table);

        Reservation savedReservation = reservationRepository.save(reservation);
        log.info("Reservation saved with id: {}", savedReservation.getId());

        List<MenuItem> menuItems = menuItemRepository.findAllById(createDto.getMenuItemIds());

        if (menuItems.size() != createDto.getMenuItemIds().size()) {
            throw new BusinessException("Some menu items not found - transaction will rollback!");
        }

        savedReservation.setPreOrderedItems(menuItems);
        table.setIsAvailable(false);
        tableRepository.save(table);

        return reservationMapper.toDto(savedReservation);
    }

    @Transactional
    public RestaurantDto createRestaurantWithTablesAndMenu(
        RestaurantCreateDto restaurantDto,
        List<TableCreateDto> tableDtos,
        List<MenuItemCreateDto> menuItemDtos) {

        log.info("=== Creating restaurant with {} tables and {} menu items ===", tableDtos.size(), menuItemDtos.size());

        Restaurant restaurant = restaurantMapper.toEntity(restaurantDto);

        for (TableCreateDto tableDto : tableDtos) {
            RestaurantTable table = new RestaurantTable();
            table.setTableNumber(tableDto.getTableNumber());
            table.setCapacity(tableDto.getCapacity());
            table.setIsAvailable(true);
            table.setLocation(tableDto.getLocation());
            restaurant.addTable(table);
        }

        Restaurant savedRestaurant = restaurantRepository.save(restaurant);

        for (MenuItemCreateDto menuItemDto : menuItemDtos) {
            MenuItem menuItem = new MenuItem();
            menuItem.setName(menuItemDto.getName());
            menuItem.setDescription(menuItemDto.getDescription());
            menuItem.setPrice(menuItemDto.getPrice());
            menuItem.setCategory(menuItemDto.getCategory());
            menuItem.setIsAvailable(true);
            menuItem.setPreparationTime(menuItemDto.getPreparationTime());
            menuItem.setImageUrl(menuItemDto.getImageUrl());
            menuItem.setRestaurant(savedRestaurant);
            menuItemRepository.save(menuItem);
        }

        return restaurantMapper.toDto(savedRestaurant);
    }

    @Transactional
    public ReservationDto updateReservationStatus(Long id, ReservationStatus status) {
        Reservation reservation = findReservationOrThrow(id);
        reservation.setStatus(status);

        if (status == ReservationStatus.CANCELLED || status == ReservationStatus.COMPLETED) {
            RestaurantTable table = reservation.getTable();
            table.setIsAvailable(true);
            tableRepository.save(table);
        }

        Reservation updatedReservation = reservationRepository.save(reservation);
        return reservationMapper.toDto(updatedReservation);
    }

    @Transactional
    public void deleteReservation(Long id) {
        Reservation reservation = findReservationOrThrow(id);
        RestaurantTable table = reservation.getTable();
        table.setIsAvailable(true);
        tableRepository.save(table);
        reservationRepository.delete(reservation);
    }

    private Reservation findReservationOrThrow(Long id) {
        return reservationRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Reservation not found with id: " + id));
    }
}