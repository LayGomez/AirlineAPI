package org.example.flight;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.airport.Airport;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "flights")
public class Flight {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, name = "id_flight")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "origin_id", nullable = false)
    private Airport originAirport;

    @ManyToOne
    @JoinColumn(name = "destination_id", nullable = false)
    private Airport destinationAirport;

    private LocalDateTime departureDate;

    private LocalDateTime arrivalDate;

    private int capacity;

    private int availableSeats;

    private boolean isAvailable;

    public Flight(Airport originAirport, Airport destinationAirport, LocalDateTime departureDate, LocalDateTime arrivalDate, int capacity, int availableSeats) {
        this.originAirport = originAirport;
        this.destinationAirport = destinationAirport;
        this.departureDate = departureDate;
        this.arrivalDate = arrivalDate;
        this.capacity = capacity;
        this.availableSeats = availableSeats;
    }

}
