package org.example.airport;

import org.example.airport.dtos.AirportRequest;
import org.example.airport.dtos.AirportResponse;
import org.example.airport.airportExceptions.AirportNotFoundException;
import org.example.country.Country;
import org.example.country.CountryExceptions.CountryNotFoundException;
import org.example.country.CountryRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AirportServices {
    private AirportRepository repository;
    private CountryRepository countryRepository;

    public AirportServices(CountryRepository countryRepository, AirportRepository repository) {
        this.countryRepository = countryRepository;
        this.repository = repository;
    }

    public AirportResponse createAirport(AirportRequest airportRequest) {
        Country country = countryRepository.findByName(airportRequest.countryName())
                .orElseThrow(() -> new CountryNotFoundException("Country not found"));

        Airport airport = AirportMapper.fromRequest(airportRequest, country);
        Airport savedAirport = repository.save(airport);

        return AirportMapper.toResponse(savedAirport);
    }

    public List<AirportResponse> searchAirportByName(String name) {
        List<Airport> airports = repository.findLikeName(name);
        return airports.stream()
                .map(AirportMapper::toResponse).toList();
    }


    public List<AirportResponse> getAirportsByCountryName(String countryName) {
        List<Airport> airports = repository.findByCountry_Name(countryName);
        return airports.stream()
                .map(AirportMapper::toResponse)
                .toList(); // Convierte a AirportResponse
    }
    public List<AirportResponse> getAllAirports() {
        List<Airport> airportList = repository.findAll();
        return airportList.stream()
                .map(AirportMapper::toResponse).toList();
    }

    public AirportResponse findAirportById(Long id) {
        Airport airport= repository.findById(id)
                .orElseThrow(() -> new AirportNotFoundException("Airline not found"));
        return AirportMapper.toResponse(airport);
    }

    public AirportResponse updateAirport(Long id, AirportRequest airportRequest) {
        Airport airportToUpdate = repository.findById(id)
                .orElseThrow(() -> new AirportNotFoundException("Airport not found"));

        Country country = airportToUpdate.getCountry();
        if (airportRequest.countryName() != country.getName()){
            country = countryRepository.findByName(airportRequest.countryName()).get();
        }

        airportToUpdate.setName(airportRequest.name());
        airportToUpdate.setCountry(country);

        Airport updatedAirport = repository.save(airportToUpdate);
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
