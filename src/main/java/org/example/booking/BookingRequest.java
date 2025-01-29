package org.example.booking;

import org.example.flight.Flight;
import org.example.Users.User;

import java.time.LocalDateTime;

public record BookingRequest(
        String username,
        int seats,
        Long id_flight
) {
}
