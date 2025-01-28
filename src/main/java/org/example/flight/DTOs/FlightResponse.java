package org.example.flight.DTOs;

import org.example.airport.Airport;

import java.time.LocalDateTime;

public record FlightResponse(
        Airport originAirport,
        Airport destinationAirport,
        LocalDateTime departureDate,
        int availableSeats,
        boolean isAvailable
) {
}
