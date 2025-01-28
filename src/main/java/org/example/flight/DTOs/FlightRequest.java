package org.example.flight.DTOs;

import org.example.airport.Airport;

import java.time.LocalDateTime;

public record FlightRequest(
        Airport originAirport,
        Airport destinationAirport,
        LocalDateTime departureDate,
        LocalDateTime arrivalDate,
        int capacity,
        int availableSeats,
        boolean isAvailable
) {
}
