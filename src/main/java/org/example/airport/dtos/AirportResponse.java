package org.example.airport.dtos;

import org.example.country.Country;

public record AirportResponse(
        Long id,
        String name,
        String country
) {
}
