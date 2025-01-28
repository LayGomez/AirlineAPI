package org.example.Profiles;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.country.Country;
import org.example.Users.User;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "profiles")
public class Profile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, name = "id_profile")
    private Long id;

    private String email;

    private String address;

    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id_user")
    private User user;

    @ManyToOne@JoinColumn(name = "country_id", nullable = true)
    private Country country;

    public Profile(User user, String address, String email, Country country) {
        this.user = user;
        this.address = address;
        this.email = email;
        this.country = country;
    }
}
