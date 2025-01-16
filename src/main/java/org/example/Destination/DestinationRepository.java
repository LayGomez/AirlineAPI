package org.example.Destination;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DestinationRepository extends JpaRepository<Destination, Long> {

    @Query(value = "SELECT d FROM Destination d WHERE LOWER(d.name) LIKE LOWER(CONCAT ('%', :name, '%'))")
    List<Destination> findLikeName(String name);
}
