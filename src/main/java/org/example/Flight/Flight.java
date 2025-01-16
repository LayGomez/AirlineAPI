package org.example.Flight;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.Destination.Destination;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Flight {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "origin_id", nullable = false)
    private Destination originAirport;

    @ManyToOne
    @JoinColumn(name = "destino_id", nullable = false)
    private Destination destinationAirport;

    @Column
    private LocalDateTime departureDate;

    @Column
    private LocalDateTime arrivalDate;

    @Column
    private int capacity;

    @Column
    private int availableSeats;

    @Column
    private boolean state;

    public Flight(Destination originAirport, Destination destinationAirport, LocalDateTime departureDate, LocalDateTime arrivalDate, int capacity, int availableSeats, boolean state) {
        this.originAirport = originAirport;
        this.destinationAirport = destinationAirport;
        this.departureDate = departureDate;
        this.arrivalDate = arrivalDate;
        this.capacity = capacity;
        this.availableSeats = availableSeats;
        this.state = state;
    }
}
