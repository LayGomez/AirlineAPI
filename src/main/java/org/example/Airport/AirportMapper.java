package org.example.Airport;

import org.example.Airport.dtos.AirportRequest;
import org.example.Airport.dtos.AirportResponse;

public class AirportMapper {
    public static Airport fromRequest (AirportRequest airportRequest){
        return new Airport(
                airportRequest.name(),
                airportRequest.country());
    }

    public static AirportResponse toResponse(Airport airport){
        return new AirportResponse(
                airport.getId(),
                airport.getName(),
                airport.getCountry()
        );
    }
}
