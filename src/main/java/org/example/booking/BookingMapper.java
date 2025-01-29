package org.example.booking;

import org.example.Users.User;
import org.example.flight.DTOs.FlightResponse;
import org.example.flight.Flight;

public class BookingMapper {
    public static Booking fromRequest(BookingRequest bookingRequest, Flight flight, User user){
        return new Booking(
                user,
                bookingRequest.seats(),
                flight
        );

    }
    public static BookingResponse toResponse(Booking booking){
        Flight flight = booking.getFlight();
        FlightResponse flightResponse = new FlightResponse(
                flight.getId(),
                flight.getOriginAirport().getName(),
                flight.getDestinationAirport().getName(),
                flight.getDepartureDate(),
                flight.getArrivalDate(),
                flight.getAvailableSeats(),
                flight.isAvailable()
        );
        return new BookingResponse(
                booking.getId(),
                booking.getUser().getUsername(),
                booking.getSeats(),
                flightResponse,
                booking.getExpiresAt()
        );
    }

}
