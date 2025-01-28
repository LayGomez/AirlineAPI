package org.example.flight;

import org.example.flight.DTOs.FlightRequest;
import org.example.flight.DTOs.FlightResponse;

public class FlightMapper {
    public static Flight fromRequest(FlightRequest flightRequest){
        return new Flight(
                flightRequest.originAirport(),
                flightRequest.destinationAirport(),
                flightRequest.departureDate(),
                flightRequest.arrivalDate(),
                flightRequest.capacity(),
                flightRequest.availableSeats(),
                flightRequest.isAvailable()
        );
    }

    public static FlightResponse toResponse(Flight flight){
            return new FlightResponse(
                    flight.getOriginAirport(),
                    flight.getDestinationAirport(),
                    flight.getDepartureDate(),
                    flight.getAvailableSeats(),
                    flight.isAvailable());
    }
}
