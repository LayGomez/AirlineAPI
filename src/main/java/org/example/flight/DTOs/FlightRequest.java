package org.example.flight.DTOs;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record FlightRequest(
        @NotBlank(message = "Origin airport must be completed")
        String originAirport,

        @NotBlank(message = "Departure airport must be completed")
        String destinationAirport,

        @NotNull(message = "Departure date cannot be empty ")
        @Future(message = "Departure date must be in the future")
        LocalDateTime departureDate,

        @NotNull(message = "Arrival date cannot be empty")
        @Future(message = "Arrival date must be in the future")
        LocalDateTime arrivalDate,

        @NotNull(message = "The capacity cannot be empty.")
        int capacity,

        @NotNull(message = "The number of places available cannot be empty")
        int availableSeats

) {
}
