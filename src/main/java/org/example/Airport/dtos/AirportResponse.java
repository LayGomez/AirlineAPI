package org.example.Airport.dtos;

import org.example.Country.Country;

public record AirportResponse(
        Long id,
        String name,
        Country country
) {
}
