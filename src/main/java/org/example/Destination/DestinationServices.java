package org.example.Destination;

import org.example.Destination.dtos.DestinationRequest;
import org.example.Destination.dtos.DestinationResponse;
import org.example.Destination.destinationExceptions.DestinationNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DestinationServices {
    private DestinationRepository repository;

    public DestinationServices(DestinationRepository destinationRepository) {
        this.repository = destinationRepository;
    }

    public DestinationResponse addNewDestination(DestinationRequest destinationRequest) {
        Destination destination = DestinationMapper.fromRequest(destinationRequest);
        Destination savedDestination = repository.save(destination);
        return DestinationMapper.toResponse(savedDestination);
    }

    public List<DestinationResponse> searchByName(String name) {
        List<Destination> destinations = repository.findLikeName(name);
        return destinations.stream()
                .map(DestinationMapper::toResponse).toList();
    }

    public List<DestinationResponse> getAllDestinations() {
        List<Destination> destinationList = repository.findAll();
        return destinationList.stream()
                .map(DestinationMapper::toResponse).toList();
    }

    public DestinationResponse findDestinationById(Long id) {
        Optional<Destination> optionalDestination = repository.findById(id);
        if (optionalDestination.isEmpty()) throw new DestinationNotFoundException("Destination Not Found");
        return DestinationMapper.toResponse(optionalDestination.get());
    }

    public DestinationResponse updateDestination(Long id, DestinationRequest destinationRequest) {
        Optional<Destination> destinationToUpdate = repository.findById(id);
        if (destinationToUpdate.isEmpty()) throw new DestinationNotFoundException("Destination Not Found");

        Destination destination = destinationToUpdate.get();
        destination.setName(destinationRequest.name());
        destination.setCountry(destinationRequest.country());

        Destination updatedDestination = repository.save(destination);
        return DestinationMapper.toResponse(updatedDestination);
    }

    public void deleteDestinationById(Long id) {
        Optional<Destination> optionalDestination = repository.findById(id);
        if (optionalDestination.isEmpty()) {
            throw new DestinationNotFoundException("Destination Not Found");
        }
        repository.deleteById(id);
    }
}
