package org.example.airport;

import org.example.airport.dtos.AirportRequest;
import org.example.airport.dtos.AirportResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("${api-endpoint}/airports")
public class AirportController {
    private final AirportServices services;

    public AirportController(AirportServices services) {
        this.services = services;
    }

    @PostMapping
    public ResponseEntity<AirportResponse> addNewAirport(@RequestBody AirportRequest airportRequest){
        AirportResponse airportResponse = services.createAirport(airportRequest);
        return ResponseEntity.status(HttpStatusCode.valueOf(201)).body(airportResponse);
    }

    @GetMapping
    public ResponseEntity<List<AirportResponse>> getAirports(
            @RequestParam(name = "name", required = false) String name,
            @RequestParam(name = "countryName", required = false) String countryName) {

        if (name != null && !name.isEmpty()) {
            return ResponseEntity.ok(services.searchAirportByName(name));
        } else if (countryName != null && !countryName.isEmpty()) {
            return ResponseEntity.ok(services.getAirportsByCountryName(countryName));
        } else {
            return ResponseEntity.ok(services.getAllAirports());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<AirportResponse> getAirportById(@PathVariable Long id){
        AirportResponse destination = services.findAirportById(id);
        return ResponseEntity.status(HttpStatusCode.valueOf(200)).body(destination);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AirportResponse> updateAirport(@PathVariable Long id, @RequestBody AirportRequest airportRequest){
        AirportResponse destination = services.updateAirport(id, airportRequest);
        return new ResponseEntity<>(destination, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public void deleteAirport(@PathVariable Long id){
        services.deleteDestinationById(id);
    }
}