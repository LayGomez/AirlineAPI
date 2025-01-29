package org.example.flight;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;

public interface FlightRepository extends JpaRepository<Flight, Long> {

    List<Flight> findByDepartureDate(LocalDateTime departureDate);

    @Query("SELECT f FROM Flight f " + "WHERE LOWER(f.originAirport.country.name) LIKE LOWER(CONCAT('%', :country, '%'))")
    List<Flight> findByOriginCountry(String country);

    @Query("SELECT f FROM Flight f " + "WHERE LOWER(f.destinationAirport.country.name) LIKE LOWER(CONCAT('%', :country, '%'))")
    List<Flight> findByDestinationCountry(String country);

    @Query("SELECT f FROM Flight f WHERE LOWER(f.originAirport.name) LIKE LOWER(CONCAT('%', :name, '%'))")
    List<Flight> findByAirportName(String airportName);

    List<Flight> findByIsAvailable(boolean b);
}
