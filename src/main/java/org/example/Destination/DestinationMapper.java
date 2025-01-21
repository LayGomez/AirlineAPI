package org.example.Destination;

import org.example.Destination.dtos.DestinationRequest;
import org.example.Destination.dtos.DestinationResponse;

public class DestinationMapper {
    public static Destination fromRequest (DestinationRequest destinationRequest){
        return new Destination(
                destinationRequest.name(),
                destinationRequest.country());
    }

    public static DestinationResponse toResponse(Destination destination){
        return new DestinationResponse(
                destination.getId(),
                destination.getName(),
                destination.getCountry()
        );
    }
}
