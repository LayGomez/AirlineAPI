package org.example.flight;

import jakarta.validation.Valid;
import org.example.flight.DTOs.FlightRequest;
import org.example.flight.DTOs.FlightResponse;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("${api-endpoint}/flights")
public class FlightController {
    private final FlightServices services;

    public FlightController(FlightServices services) {
        this.services = services;
    }

    @PostMapping
    public ResponseEntity<FlightResponse> addNewFlight(@Valid @RequestBody FlightRequest flightRequest){
        FlightResponse flightResponse = services.addNewFlight(flightRequest);
        return ResponseEntity.status(HttpStatusCode.valueOf(201)).body(flightResponse);
    }

    @GetMapping
    public List<FlightResponse> showAllFlights(@RequestParam(name = "country", required = false)String country){
        return services.getAllFlights();
    }

    @GetMapping("/{id}")
    public ResponseEntity<FlightResponse> getFlightById(@PathVariable Long id){
        FlightResponse flight = services.findFlightById(id);
        return ResponseEntity.status(HttpStatusCode.valueOf(200)).body(flight);
    }

    @GetMapping("/search/by-origin-country")
    public ResponseEntity<List<FlightResponse>> searchByOriginCountry(@RequestParam String country) {
        List<FlightResponse> flights = services.findFlightsByOriginCountry(country);
        return ResponseEntity.ok(flights);
    }

    @GetMapping("/search/by-destination-country")
    public ResponseEntity<List<FlightResponse>> searchByDestinationCountry(@RequestParam String country) {
        List<FlightResponse> flights = services.findFlightsByDestinationCountry(country);
        return ResponseEntity.ok(flights);
    }
/*
    @GetMapping("/search/by-departure-date")
    public ResponseEntity<List<Flight>> searchByDepartureDate(@RequestParam LocalDateTime departureDate) {
        List<Flight> flights = services.findFlightsByDepartureDate(departureDate);
        return ResponseEntity.ok(flights);
    }



    @GetMapping("/search/by-airport")
    public ResponseEntity<List<Flight>> searchByAirport(@RequestParam String airportName) {
        List<Flight> flights = services.findFlightsByAirportName(airportName);
        return ResponseEntity.ok(flights);
    }*/

    /*
    @GetMapping("/{id}")

    @PutMapping("/{id}")
    public ResponseEntity<FlightResponse> updateFlight (@PathVariable Long id, @RequestBody FlightRequest flightRequest){
        FlightResponse flight = services.updateFlight(id, flightRequest);
        return new ResponseEntity<>(flight, HttpStatus.OK);
    }
*/
    @DeleteMapping("/{id}")
    public void deleteFlight(@PathVariable Long id) {
        services.deleteFlightById(id);
    }
}
