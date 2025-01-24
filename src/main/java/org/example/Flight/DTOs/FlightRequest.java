package org.example.Flight.DTOs;

import org.example.Airport.Airport;

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
