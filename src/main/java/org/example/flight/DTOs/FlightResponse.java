package org.example.flight.DTOs;

import org.example.airport.Airport;

import java.time.LocalDateTime;

public record FlightResponse(
        Long id,
        String originAirport,
        String destinationAirport,
        LocalDateTime departureDate,
        LocalDateTime arrivalDate,
        int availableSeats,
        boolean isAvailable
) {
}
