package org.example.airport;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.country.Country;
import org.example.flight.Flight;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Airport {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, name= "id_airport")
    private Long id;

    @Column
    private String name;

    @ManyToOne
    @JoinColumn(name = "id_country", nullable = false)
    private Country country;

    @OneToMany(mappedBy = "originAirport")
    @JsonIgnore
    private List<Flight> originFlights;

    @OneToMany(mappedBy = "destinationAirport")
    @JsonIgnore
    private List<Flight> destinationFlights;

    public Airport(String name, Country country) {
        this.name = name;
        this.country = country;
    }

}
