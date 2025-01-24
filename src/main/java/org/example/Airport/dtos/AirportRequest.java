package org.example.Airport.dtos;

import org.example.Country.Country;

public record AirportRequest(
        String name,
        Country country
) {
}
