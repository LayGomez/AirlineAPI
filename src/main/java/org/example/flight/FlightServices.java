package org.example.flight;

import org.example.airport.Airport;
import org.example.airport.AirportRepository;
import org.example.airport.airportExceptions.AirportNotFoundException;
import org.example.flight.DTOs.FlightRequest;
import org.example.flight.DTOs.FlightResponse;
import org.example.flight.FlightExceptions.FlightException;
import org.example.flight.FlightExceptions.FlightNotFoundException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class FlightServices {

    private FlightMapper flightMapper;
    private FlightRepository repository;
    private AirportRepository airportRepository;

    public FlightServices(FlightMapper flightMapper, AirportRepository airportRepository, FlightRepository repository) {
        this.flightMapper = flightMapper;
        this.airportRepository = airportRepository;
        this.repository = repository;
    }

    public FlightResponse addNewFlight(FlightRequest flightRequest){

        if (flightRequest.originAirport().equalsIgnoreCase(flightRequest.destinationAirport())) {
            throw new FlightException("The departure airport cannot be the same as the destination. ");
        }

        if (flightRequest.departureDate().isBefore(LocalDateTime.now())) {
            throw new FlightException("The departure date cannot be earlier than the current date.");
        }

        if (flightRequest.arrivalDate().isBefore(flightRequest.departureDate())) {
            throw new FlightException("Arrival date must be after departure date.");
        }


        Flight flight = flightMapper.fromRequest(flightRequest);
        Flight savedFlight = repository.save(flight);

        return FlightMapper.toResponse(savedFlight);
    }


    public List<FlightResponse> getAllFlights() {
        String role = getCurrentUserRole();
        boolean onlyAvailable = !role.equals("ROLE_ADMIN");

        if (onlyAvailable) {
            List<Flight> flightList = repository.findByIsAvailable(true);
            return flightList.stream()
                    .map(FlightMapper::toResponse)
                    .toList();
        } else {
            List<Flight> flightList = repository.findAll();
            return flightList.stream()
                    .map(FlightMapper::toResponse)
                    .toList();
        }
    }

    public FlightResponse findFlightById(Long id) {
        Optional<Flight> optionalFlight = repository.findById(id);
        if (optionalFlight.isEmpty()) throw new FlightNotFoundException("Flight Not Found");
        return FlightMapper.toResponse(optionalFlight.get());
    }


    public List<FlightResponse> findFlightsByOriginCountry(String country) {
        List<Flight> flightList = repository.findByOriginCountry(country);
        return flightList.stream()
                .map(FlightMapper::toResponse)
                .toList();
    }

    public List<FlightResponse> findFlightsByDestinationCountry(String country) {
        List<Flight> flightList = repository.findByDestinationCountry(country);
        return flightList.stream()
                .map(FlightMapper::toResponse)
                .toList();
    }

/*
// search by departure date
    public List<Flight> findFlightsByDepartureDate(LocalDateTime departureDate) {
        return repository.findByDepartureDate(departureDate);
    }
*/

    public FlightResponse updateFlight(Long id, FlightRequest flightRequest) {
        Optional<Flight> flightToUpdate = repository.findById(id);
        if (flightToUpdate.isEmpty()) throw new FlightNotFoundException("Flight Not Found");

        Airport originAirport = airportRepository.findByName(flightRequest.originAirport())
                .orElseThrow(() -> new AirportNotFoundException("Origin airport not found: " + flightRequest.originAirport()));

        Airport destinationAirport = airportRepository.findByName(flightRequest.destinationAirport())
                .orElseThrow(() -> new AirportNotFoundException("Destination airport not found: " + flightRequest.destinationAirport()));

        Flight flight = flightToUpdate.get();
        flight.setOriginAirport(originAirport);
        flight.setDestinationAirport(destinationAirport);
        flight.setDepartureDate(flightRequest.departureDate());
        flight.setArrivalDate(flightRequest.arrivalDate());
        flight.setCapacity(flightRequest.capacity());
        flight.setAvailableSeats(flightRequest.availableSeats());

        Flight updatedFlight = repository.save(flight);
        return FlightMapper.toResponse(updatedFlight);
    }

    public void deleteFlightById(Long id) {
        Optional<Flight> optionalFlight = repository.findById(id);
        if (optionalFlight.isEmpty()) {
            throw new FlightNotFoundException("Flight Not Found");
        }
        repository.deleteById(id);
    }

    private String getCurrentUserRole() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication.getAuthorities().stream()
                .findFirst()
                .orElseThrow(() -> new RuntimeException("unauthenticated user"))
                .getAuthority();
    }


}
