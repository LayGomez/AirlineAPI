package org.example.Airport;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.Country.Country;
import org.example.Flight.Flight;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Airport {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;

    @Column
    private String name;

    @ManyToOne
    @JoinColumn(name = "country_id", nullable = false)
    private Country country;

    @OneToMany(mappedBy = "originAirport")
    private List<Flight> originFlights;

    @OneToMany(mappedBy = "destinationAirport")
    private List<Flight> destinationFlights;

    public Airport(String name, Country country) {
        this.name = name;
        this.country = country;
    }

}
