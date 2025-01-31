package org.example.booking;

import org.example.Users.User;
import org.example.Users.UserExceptions.UserException;
import org.example.Users.UserExceptions.UserNotFoundException;
import org.example.Users.UserRepository;
import org.example.booking.BookingExceptions.BookingNotFoundException;
import org.example.flight.Flight;
import org.example.flight.FlightExceptions.FlightException;
import org.example.flight.FlightExceptions.FlightNotFoundException;
import org.example.flight.FlightRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookingServices {
    private BookingRepository repository;
    private UserRepository userRepository;
    private final FlightRepository flightRepository;

    public BookingServices(BookingRepository repository, UserRepository userRepository,
                           FlightRepository flightRepository) {
        this.repository = repository;
        this.userRepository = userRepository;
        this.flightRepository = flightRepository;
    }

    public BookingResponse createBooking(BookingRequest bookingRequest) {

        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UserNotFoundException("User not found: " + username));

        Flight flight = flightRepository.findById(bookingRequest.id_flight())
                .orElseThrow(() -> new FlightNotFoundException("Flight not found: " + bookingRequest.id_flight()));
        if (!flight.isAvailable()) {
            throw new FlightException("This flight is not available for booking.");
        }
        if (flight.getAvailableSeats() < bookingRequest.seats()) {
            throw new FlightException("Not enough available seats for this flight.");
        }

        Booking booking = BookingMapper.fromRequest(bookingRequest, flight, user);

        flight.setAvailableSeats(flight.getAvailableSeats() - bookingRequest.seats());
        flightRepository.save(flight);

        Booking savedBooking = repository.save(booking);
        return BookingMapper.toResponse(savedBooking);
    }

    public List<BookingResponse> getAllBookingsForUser() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();

        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UserNotFoundException("User not found: " + username));

        List<Booking> bookings = repository.findByUser(user);
        return bookings.stream()
                .map(BookingMapper::toResponse)
                .toList();
    }

    public List<BookingResponse> getAllBookings(){
        List<Booking> bookingList = repository.findAll();
        return bookingList.stream()
                .map(BookingMapper::toResponse)
                .toList();
    }
    public BookingResponse updateBooking(Long id, BookingRequest request) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Booking booking = repository.findById(id)
                .orElseThrow(() -> new BookingNotFoundException("Booking not found with id: " + id));

        if (!booking.getUser().getUsername().equals(username)) {
            throw new UserException("You can only modify your own bookings.");
        }

        if (request.id_flight() != null && !request.id_flight().equals(booking.getFlight().getId())) {
            Flight newFlight = flightRepository.findById(request.id_flight())
                    .orElseThrow(() -> new FlightNotFoundException("Flight not found with id: " + request.id_flight()));
            booking.setFlight(newFlight);
        }

        booking.setSeats(request.seats());

        Booking updatedBooking = repository.save(booking);
        return BookingMapper.toResponse(updatedBooking);
    }


    public void deleteMyBooking(Long id) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();

        Booking booking = repository.findById(id)
                .orElseThrow(() -> new BookingNotFoundException("Booking not found with id: " + id));

        if (!booking.getUser().getUsername().equals(username)) {
            throw new UserException("You can only delete your own bookings.");
        }

        Flight flight = booking.getFlight();
        flight.setAvailableSeats(flight.getAvailableSeats() + booking.getSeats());
        flightRepository.save(flight);

        repository.deleteById(id);
    }
}
