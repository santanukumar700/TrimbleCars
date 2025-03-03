package com.trimblecars.controller;

import com.trimblecars.entity.Car;
import com.trimblecars.entity.Lease;
import com.trimblecars.service.CarService;
import com.trimblecars.service.LeaseService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Collections;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
public class CarLeaseControllerTest {

    @Mock
    private CarService carService;

    @Mock
    private LeaseService leaseService;

    @InjectMocks
    private CarController carController;

    @InjectMocks
    private LeaseController leaseController;

    private MockMvc mockMvc;

    @Test
    void testGetAllCars() throws Exception {
        mockMvc = MockMvcBuilders.standaloneSetup(carController).build();

        when(carService.getAllCars()).thenReturn(Collections.emptyList());

        mockMvc.perform(get("/cars"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json("[]"));

        verify(carService, times(1)).getAllCars();
    }

    @Test
    void testStartLease() throws Exception {
        mockMvc = MockMvcBuilders.standaloneSetup(leaseController).build();
        Lease lease = new Lease();
        when(leaseService.startLease(any(Lease.class))).thenReturn(lease);

        mockMvc.perform(post("/leases")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{}"))
                .andExpect(status().isOk());

        verify(leaseService, times(1)).startLease(any(Lease.class));
    }
}