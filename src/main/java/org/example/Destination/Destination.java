package org.example.Destination;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.Flight.Flight;

import java.util.List;


@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Destination {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;

    @Column
    private String name;

    @Column
    private String country;

    @OneToMany(mappedBy = "originAirport")
    private List<Flight> originFlights;

    @OneToMany(mappedBy = "destinationAirport")
    private List<Flight> destinationFlights;

    public Destination(String name, String country) {
        this.name = name;
        this.country = country;
    }
}
