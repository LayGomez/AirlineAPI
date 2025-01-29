package org.example.task;

import org.example.booking.Booking;
import org.example.booking.BookingRepository;
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
    private final BookingRepository bookingRepository;
    private FlightRepository flightRepository;

    public SchedulingConfig(BookingRepository bookingRepository, FlightRepository flightRepository) {
        this.bookingRepository = bookingRepository;
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

    @Scheduled(fixedRate = 60000)
    public void releaseExpiredBookings() {
        LocalDateTime now = LocalDateTime.now();
        List<Booking> expiredBookings = bookingRepository.findByExpiresAtBefore(now);

        for (Booking booking : expiredBookings) {
            Flight flight = booking.getFlight();
            flight.setAvailableSeats(flight.getAvailableSeats() + booking.getSeats());
            flightRepository.save(flight);
            bookingRepository.delete(booking);
        }
    }
}
