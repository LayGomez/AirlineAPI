package org.example.Airport;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AirportRepository extends JpaRepository<Airport, Long> {

    @Query(value = "SELECT a FROM Airport a WHERE LOWER(a.name) LIKE LOWER(CONCAT ('%', :name, '%'))")
    List<Airport> findLikeName(String name);
}
