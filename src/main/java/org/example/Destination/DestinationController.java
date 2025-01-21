package org.example.Destination;

import org.example.Destination.dtos.DestinationRequest;
import org.example.Destination.dtos.DestinationResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("${api-endpoint}/destinations")
public class DestinationController {
    private final DestinationServices services;

    public DestinationController(DestinationServices services) {
        this.services = services;
    }

    @PostMapping
    public ResponseEntity<DestinationResponse> addNewDestination(@RequestBody DestinationRequest destinationRequest){
        DestinationResponse destinationResponse = services.addNewDestination(destinationRequest);
        return ResponseEntity.status(HttpStatusCode.valueOf(201)).body(destinationResponse);
    }

    @GetMapping
    public List<DestinationResponse> viewAllDestinations(@RequestParam(name = "name", required = false) String name){
        if (name != null && !name.isEmpty()){
            return services.searchByName(name);
        }
        return services.getAllDestinations();
    }

    @GetMapping("/{id}")
    public ResponseEntity<DestinationResponse> getDestinationById(@PathVariable Long id){
        DestinationResponse destination = services.findDestinationById(id);
        return ResponseEntity.status(HttpStatusCode.valueOf(200)).body(destination);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DestinationResponse> updateDestination (@PathVariable Long id, @RequestBody DestinationRequest destinationRequest){
        DestinationResponse destination = services.updateDestination(id, destinationRequest);
        return new ResponseEntity<>(destination, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public void deleteDestination(@PathVariable Long id){
        services.deleteDestinationById(id);
    }
}