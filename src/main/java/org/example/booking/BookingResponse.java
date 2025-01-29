package org.example.booking;

import org.example.flight.DTOs.FlightResponse;

import java.time.LocalDateTime;

public record BookingResponse(
        Long id,
        String username,
        int seats,
        FlightResponse flight,
        LocalDateTime date
) {}
