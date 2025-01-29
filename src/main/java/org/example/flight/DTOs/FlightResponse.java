package org.example.flight.DTOs;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.example.airport.Airport;

import java.time.LocalDateTime;

public record FlightResponse(
        Long id,
        @JsonProperty("originAirport")
        String originAirportName,
        @JsonProperty("destinationAirport")
        String destinationAirportName,
        LocalDateTime departureDate,
        LocalDateTime arrivalDate,
        int availableSeats,
        boolean available
) {}