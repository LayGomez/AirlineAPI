package org.example.Flight.DTOs;

import org.example.Airport.Airport;

import java.time.LocalDateTime;

public record FlightResponse(
        Airport originAirport,
        Airport destinationAirport,
        LocalDateTime departureDate,
        int availableSeats,
        boolean isAvailable
) {
}
