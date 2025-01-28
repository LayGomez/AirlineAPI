package org.example.airport;

import org.example.airport.dtos.AirportRequest;
import org.example.airport.dtos.AirportResponse;
import org.example.country.Country;

public class AirportMapper {
    public static Airport fromRequest (AirportRequest airportRequest, Country country){
        return new Airport(
                airportRequest.name(),
                country);
    }

    public static AirportResponse toResponse(Airport airport){
        return new AirportResponse(
                airport.getId(),
                airport.getName(),
                airport.getCountry().getName()
        );
    }
}
