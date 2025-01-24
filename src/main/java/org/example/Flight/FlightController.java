package org.example.Flight;

import org.example.Airport.Airport;
import org.example.Airport.dtos.AirportRequest;
import org.example.Airport.dtos.AirportResponse;
import org.example.Flight.DTOs.FlightRequest;
import org.example.Flight.DTOs.FlightResponse;
import org.springframework.http.HttpStatus;
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
    public ResponseEntity<FlightResponse> addNewFLight(@RequestBody FlightRequest flightRequest){
        FlightResponse flightResponse = services.newFlight(flightRequest);
        return ResponseEntity.status(HttpStatusCode.valueOf(201)).body(flightResponse);
    }

    @GetMapping
    public List<FlightResponse> showAllFlights(@RequestParam(name = "country", required = false)String country){
        /* if (country != null && !country.isEmpty()){
            return services.searchFlightByCountry(country);
        } */
        return services.getAllFlights();
    }

    @GetMapping("/{id}")
    public ResponseEntity<FlightResponse> getFlightById(@PathVariable Long id){
        FlightResponse flight = services.findFlightById(id);
        return ResponseEntity.status(HttpStatusCode.valueOf(200)).body(flight);
    }

    @PutMapping("/{id}")
    public ResponseEntity<FlightResponse> updateFlight (@PathVariable Long id, @RequestBody FlightRequest flightRequest){
        FlightResponse flight = services.updateFlight(id, flightRequest);
        return new ResponseEntity<>(flight, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public void deleteFlight(@PathVariable Long id) {
        services.deleteFlightById(id);
    }
}
