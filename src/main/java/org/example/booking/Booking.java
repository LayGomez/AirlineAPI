package org.example.booking;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.flight.Flight;
import org.example.Users.User;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_user", nullable = false)
    private User user;

    @Column(nullable = false)
    private int seats;

    @ManyToOne
    @JoinColumn(name = "flight_id", nullable = false)
    private Flight flight;

    @Column(nullable = false)
    private LocalDateTime expiresAt;

    public Booking(User user, int seats, Flight flight) {
        this.user = user;
        this.seats = seats;
        this.flight = flight;
    }
    @PrePersist
    public void setExpirationTime() {
        this.expiresAt = LocalDateTime.now().plusMinutes(15);
    }
}
