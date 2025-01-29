package org.example.flight;

import org.example.airport.Airport;
import org.example.airport.AirportRepository;
import org.example.airport.airportExceptions.AirportNotFoundException;
import org.example.flight.DTOs.FlightRequest;
import org.example.flight.DTOs.FlightResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FlightMapper {

    @Autowired
    private AirportRepository airportRepository;


    public Flight fromRequest(FlightRequest flightRequest){
        Airport originAirport = airportRepository.findByName(flightRequest.originAirport())
                .orElseThrow(() -> new AirportNotFoundException("Origin airport not found: " + flightRequest.originAirport()));

        Airport destinationAirport = airportRepository.findByName(flightRequest.destinationAirport())
                .orElseThrow(() -> new AirportNotFoundException("Departure airport not found: " + flightRequest.destinationAirport()));

        return new Flight(
                originAirport,
                destinationAirport,
                flightRequest.departureDate(),
                flightRequest.arrivalDate(),
                flightRequest.capacity(),
                flightRequest.capacity(),
                flightRequest.isAvailable()
        );
    }

    public static FlightResponse toResponse(Flight flight){
            return new FlightResponse(
                    flight.getId(),
                    flight.getOriginAirport().getName(),
                    flight.getDestinationAirport().getName(),
                    flight.getDepartureDate(),
                    flight.getArrivalDate(),
                    flight.getAvailableSeats(),
                    flight.isAvailable());
    }
}
