package org.example.airport;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AirportRepository extends JpaRepository<Airport, Long> {

    @Query(value = "SELECT a FROM Airport a WHERE LOWER(a.name) LIKE LOWER(CONCAT ('%', :name, '%'))")
    List<Airport> findLikeName(String name);

    @Query(value = "SELECT a FROM Airport a WHERE LOWER(a.country.name) LIKE LOWER(CONCAT('%', :countryName, '%'))")
    List<Airport> findByCountry_Name(String countryName);
}
