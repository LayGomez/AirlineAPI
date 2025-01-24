package org.example.Flight;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.Airport.Airport;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Flight {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, name = "flight_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "origin_id", nullable = false)
    private Airport originAirport;

    @ManyToOne
    @JoinColumn(name = "destino_id", nullable = false)
    private Airport destinationAirport;

    private LocalDateTime departureDate;

    private LocalDateTime arrivalDate;

    private int capacity;

    private int availableSeats;

    private boolean isAvailable;

    public Flight(Airport originAirport, Airport destinationAirport, LocalDateTime departureDate, LocalDateTime arrivalDate, int capacity, int availableSeats, boolean isAvailable) {
        this.originAirport = originAirport;
        this.destinationAirport = destinationAirport;
        this.departureDate = departureDate;
        this.arrivalDate = arrivalDate;
        this.capacity = capacity;
        this.availableSeats = availableSeats;
        this.isAvailable = isAvailable;
    }
}
