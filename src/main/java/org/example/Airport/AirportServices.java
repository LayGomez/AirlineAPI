package org.example.Airport;

import org.example.Airport.dtos.AirportRequest;
import org.example.Airport.dtos.AirportResponse;
import org.example.Airport.airportExceptions.AirportNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AirportServices {
    private AirportRepository repository;

    public AirportServices(AirportRepository airportRepository) {
        this.repository = airportRepository;
    }

    public AirportResponse addNewDestination(AirportRequest airportRequest) {
        Airport airport = AirportMapper.fromRequest(airportRequest);
        Airport savedAirport = repository.save(airport);
        return AirportMapper.toResponse(savedAirport);
    }

    public List<AirportResponse> searchByName(String name) {
        List<Airport> airports = repository.findLikeName(name);
        return airports.stream()
                .map(AirportMapper::toResponse).toList();
    }

    public List<AirportResponse> getAllDestinations() {
        List<Airport> airportList = repository.findAll();
        return airportList.stream()
                .map(AirportMapper::toResponse).toList();
    }

    public AirportResponse findDestinationById(Long id) {
        Optional<Airport> optionalDestination = repository.findById(id);
        if (optionalDestination.isEmpty()) throw new AirportNotFoundException("Airport Not Found");
        return AirportMapper.toResponse(optionalDestination.get());
    }

    public AirportResponse updateDestination(Long id, AirportRequest airportRequest) {
        Optional<Airport> destinationToUpdate = repository.findById(id);
        if (destinationToUpdate.isEmpty()) throw new AirportNotFoundException("Airport Not Found");

        Airport airport = destinationToUpdate.get();
        airport.setName(airportRequest.name());
        airport.setCountry(airportRequest.country());

        Airport updatedAirport = repository.save(airport);
        return AirportMapper.toResponse(updatedAirport);
    }

    public void deleteDestinationById(Long id) {
        Optional<Airport> optionalDestination = repository.findById(id);
        if (optionalDestination.isEmpty()) {
            throw new AirportNotFoundException("Airport Not Found");
        }
        repository.deleteById(id);
    }
}
