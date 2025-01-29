package org.example.task;

import org.example.flight.Flight;
import org.example.flight.FlightRepository;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.time.LocalDateTime;
import java.util.List;

@Configuration
@EnableScheduling
public class SchedulingConfig {
    private FlightRepository flightRepository;

    public SchedulingConfig(FlightRepository flightRepository) {
        this.flightRepository = flightRepository;
    }

    @Scheduled(fixedRate = 30000)
    public void updateFlightsAvailability() {
        List<Flight> flights = flightRepository.findAll();
        for (Flight flight : flights) {
            boolean shouldBeAvailable = flight.getAvailableSeats() > 0 &&
                    flight.getDepartureDate().isAfter(LocalDateTime.now());

            if (flight.isAvailable() != shouldBeAvailable) {
                flight.setAvailable(shouldBeAvailable);
                flightRepository.save(flight);
            }
        }
    }
}
