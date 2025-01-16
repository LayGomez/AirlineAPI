package org.example.Destination;

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
