package org.example.Destination;

import jakarta.websocket.server.PathParam;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/destinations")
public class DestinationController {
    DestinationService destinationService;

    public DestinationController(DestinationService destinationService) {
        this.destinationService = destinationService;
    }

    @PostMapping
    public ResponseEntity<DestinationResponse> addNewDestination(@RequestBody DestinationRequest destinationRequest){
        DestinationResponse destinationResponse = destinationService.addNewDestination(destinationRequest);
        return new ResponseEntity<>(destinationResponse, HttpStatus.CREATED);
    }

    @GetMapping
    public List<DestinationResponse> getAllDestinations(@RequestParam(name = "name", required = false) String name){
        if (name != null && !name.isEmpty()){
            return destinationService.searchByName(name);
        }
        return destinationService.getAllDestinations();
    }

    @GetMapping
    public DestinationResponse getDestinationById(@PathVariable Long id){
        return destinationService.findDestinationById(id);
    }

    @PutMapping
    public ResponseEntity<DestinationResponse> updateDestination (@PathVariable Long id, @RequestBody DestinationRequest destinationRequest){
        DestinationResponse destination = destinationService.updateDestination(id, destinationRequest);
        return new ResponseEntity<>(destination, HttpStatus.OK);
    }

    @DeleteMapping
    public void deleteDestination(@PathVariable Long id){
        destinationService.deleteDestinationById(id);
    }
}
