package org.example.booking;

import org.example.flight.Flight;
import org.example.Users.User;

import java.time.LocalDateTime;

public record BookingDTO(
        User user,
        int seats,
        Flight flight,
        LocalDateTime date
) {
}
