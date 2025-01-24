package org.example.Flight;

import org.example.Flight.DTOs.FlightRequest;
import org.example.Flight.DTOs.FlightResponse;
import org.example.Flight.FlightExceptions.FlightNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FlightServices {
    private FlightRepository repository;

    public FlightServices(FlightRepository repository) {
        this.repository = repository;
    }

    public FlightResponse newFlight(FlightRequest flightRequest){
        Flight flight = FlightMapper.fromRequest(flightRequest);
        Flight savedFlight = repository.save(flight);
        return FlightMapper.toResponse(savedFlight);
    }

    public List<FlightResponse> getAllFlights() {
        List<Flight> flightList = repository.findAll();
        return flightList.stream()
                .map(FlightMapper::toResponse).toList();
    }

    /*
    public List<FlightResponse> searchFlightByCountry(String country) {

    }*/

    public FlightResponse findFlightById(Long id) {
        Optional<Flight> optionalFlight = repository.findById(id);
        if (optionalFlight.isEmpty()) throw new FlightNotFoundException("Flight Not Found");
        return FlightMapper.toResponse(optionalFlight.get());
    }

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
    }

    public void deleteFlightById(Long id) {
        Optional<Flight> optionalFlight = repository.findById(id);
        if (optionalFlight.isEmpty()) {
            throw new
                    FlightNotFoundException("Flight Not Found");
        }
        repository.deleteById(id);
    }


}
