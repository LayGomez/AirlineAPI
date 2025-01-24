package org.example.Airport;

import org.example.Airport.dtos.AirportRequest;
import org.example.Airport.dtos.AirportResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("${api-endpoint}/destinations")
public class AirportController {
    private final AirportServices services;

    public AirportController(AirportServices services) {
        this.services = services;
    }

    @PostMapping
    public ResponseEntity<AirportResponse> addNewDestination(@RequestBody AirportRequest airportRequest){
        AirportResponse airportResponse = services.addNewDestination(airportRequest);
        return ResponseEntity.status(HttpStatusCode.valueOf(201)).body(airportResponse);
    }

    @GetMapping
    public List<AirportResponse> viewAllDestinations(@RequestParam(name = "name", required = false) String name){
        if (name != null && !name.isEmpty()){
            return services.searchByName(name);
        }
        return services.getAllDestinations();
    }

    @GetMapping("/{id}")
    public ResponseEntity<AirportResponse> getDestinationById(@PathVariable Long id){
        AirportResponse destination = services.findDestinationById(id);
        return ResponseEntity.status(HttpStatusCode.valueOf(200)).body(destination);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AirportResponse> updateDestination (@PathVariable Long id, @RequestBody AirportRequest airportRequest){
        AirportResponse destination = services.updateDestination(id, airportRequest);
        return new ResponseEntity<>(destination, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public void deleteDestination(@PathVariable Long id){
        services.deleteDestinationById(id);
    }
}