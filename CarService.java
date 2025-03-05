package com.trimblecars.service;

import com.trimblecars.entity.Car;
import com.trimblecars.entity.Lease;
import com.trimblecars.entity.User;
import com.trimblecars.repository.CarRepository;
import com.trimblecars.repository.LeaseRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Optional;

import static javax.management.Query.times;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CarLeaseServiceTest {

    @Mock
    private CarRepository carRepository;

    @Mock
    private LeaseRepository leaseRepository;

    @InjectMocks
    private CarService carService;

    @InjectMocks
    private LeaseService leaseService;

    private Car car;
    private Lease lease;
    private User owner;
    private User customer;

    @BeforeEach
    void setUp() {
        owner = new User();
        owner.setId(1L);
        owner.setName("John Doe");
        owner.setEmail("john@example.com");

        customer = new User();
        customer.setId(2L);
        customer.setName("Jane Doe");
        customer.setEmail("jane@example.com");

        car = new Car();
        car.setId(1L);
        car.setModel("Tesla Model S");
        car.setOwner(owner);

        lease = new Lease();
        lease.setId(1L);
        lease.setCar(car);
        lease.setCustomer(customer);
        lease.setLeaseStart(LocalDate.now());
    }

    @Test
    void testRegisterCar() {
        when(carRepository.save(any(Car.class))).thenReturn(car);

        Car savedCar = carService.registerCar(car);

        assertNotNull(savedCar);
        assertEquals("Tesla Model S", savedCar.getModel());
        verify(carRepository, times(1)).save(car);
    }

    @Test
    void testStartLease() {
        when(leaseRepository.save(any(Lease.class))).thenReturn(lease);

        Lease startedLease = leaseService.startLease(lease);

        assertNotNull(startedLease);
        assertEquals(LocalDate.now(), startedLease.getLeaseStart());
        verify(leaseRepository, times(1)).save(lease);
    }

    @Test
    void testEndLease() {
        when(leaseRepository.findById(1L)).thenReturn(Optional.of(lease));
        when(leaseRepository.save(any(Lease.class))).thenReturn(lease);

        Lease endedLease = leaseService.endLease(1L);

        assertNotNull(endedLease);
        assertNotNull(endedLease.getLeaseEnd());
        verify(leaseRepository, times(1)).save(lease);
    }
}

