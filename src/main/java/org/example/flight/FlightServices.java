package org.example.flight;

import org.example.flight.DTOs.FlightRequest;
import org.example.flight.DTOs.FlightResponse;
import org.example.flight.FlightExceptions.FlightException;
import org.example.flight.FlightExceptions.FlightNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class FlightServices {

    private FlightMapper flightMapper;

    private FlightRepository repository;

    public FlightServices(FlightMapper flightMapper, FlightRepository repository) {
        this.flightMapper = flightMapper;
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

        updateFlightAvailability(savedFlight.getId());

        return FlightMapper.toResponse(savedFlight);
    }


    public List<FlightResponse> getAllFlights() {
        List<Flight> flightList = repository.findAll();
        return flightList.stream()
                .map(FlightMapper::toResponse).toList();
    }

    public FlightResponse findFlightById(Long id) {
        Optional<Flight> optionalFlight = repository.findById(id);
        if (optionalFlight.isEmpty()) throw new FlightNotFoundException("Flight Not Found");
        return FlightMapper.toResponse(optionalFlight.get());
    }
    public void updateFlightAvailability(Long flightId) {
        Flight flight = repository.findById(flightId)
                .orElseThrow(() -> new FlightNotFoundException("Flight not found"));

        if (flight.getAvailableSeats() <= 0 || flight.getDepartureDate().isBefore(LocalDateTime.now())) {
            flight.setAvailable(false);
            repository.save(flight);
        }
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

   // search by airport name
    public List<Flight> findFlightsByAirportName(String airportName) {
        return repository.findByAirportName(airportName);
    }
*/



/*

    public FlightResponse updateFlight(Long id, FlightRequest flightRequest) {
        Optional<Flight> flightToUpdate = repository.findById(id);
        if (flightToUpdate.isEmpty()) throw new FlightNotFoundException("Flight Not Found");

        Flight flight = flightToUpdate.get();
        flight.setOriginAirport(flightRequest.originAirport());
        flight.setDestinationAirport(flightRequest.destinationAirport());
        flight.setDepartureDate(flightRequest.departureDate());
        flight.setArrivalDate(flightRequest.arrivalDate());
        flight.setCapacity(flightRequest.capacity());
        flight.setAvailableSeats(flightRequest.availableSeats());
        flight.setAvailable(flightRequest.isAvailable());

        Flight updatedFlight = repository.save(flight);
        return FlightMapper.toResponse(updatedFlight);
    }*/

    public void deleteFlightById(Long id) {
        Optional<Flight> optionalFlight = repository.findById(id);
        if (optionalFlight.isEmpty()) {
            throw new FlightNotFoundException("Flight Not Found");
        }
        repository.deleteById(id);
    }


}
