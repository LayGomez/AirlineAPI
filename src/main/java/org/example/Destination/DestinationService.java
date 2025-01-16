package org.example.Destination;

import org.example.Exceptions.DestinationNotFoundException;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Service
public class DestinationService {
    private DestinationRepository destinationRepository;

    public DestinationService(DestinationRepository destinationRepository) {
        this.destinationRepository = destinationRepository;
    }

    public DestinationResponse addNewDestination(DestinationRequest destinationRequest) {
        Destination destination = DestinationMapper.fromRequest(destinationRequest);
        Destination savedDestination = destinationRepository.save(destination);
        return DestinationMapper.toResponse(savedDestination);
    }

    public List<DestinationResponse> searchByName(String name) {
        List<Destination> destinations = destinationRepository.findLikeName(name);
        return destinations.stream()
                .map(DestinationMapper::toResponse).toList();
    }

    public List<DestinationResponse> getAllDestinations() {
        List<Destination> destinationList = destinationRepository.findAll();
        return destinationList.stream()
                .map(DestinationMapper::toResponse).toList();
    }

    public DestinationResponse findDestinationById(Long id) {
        Optional<Destination> optionalDestination = destinationRepository.findById(id);
        if (optionalDestination.isEmpty()) throw new DestinationNotFoundException("Destination Not Found");
        return DestinationMapper.toResponse(optionalDestination.get());
    }

    public DestinationResponse updateDestination(Long id, DestinationRequest destinationRequest) {
        Optional<Destination> destinationToUpdate = destinationRepository.findById(id);
        if (destinationToUpdate.isEmpty()) throw new DestinationNotFoundException("Destination Not Found");

        Destination destination = destinationToUpdate.get();
        destination.setName(destinationRequest.name());
        destination.setCountry(destinationRequest.country());

        Destination updatedDestination = destinationRepository.save(destination);
        return DestinationMapper.toResponse(updatedDestination);
    }

    public void deleteDestinationById(Long id) {
        Optional<Destination> optionalDestination = destinationRepository.findById(id);
        if (optionalDestination.isEmpty()) {
            throw new DestinationNotFoundException("Destination Not Found");
        }
        destinationRepository.deleteById(id);
    }
}
