package pl.bohdanlukaszewicz.JavaPortApp.User.Model;

import jakarta.persistence.*;
import lombok.*;
import pl.bohdanlukaszewicz.JavaPortApp.Enums.Role;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String passwordHashed;

    @Column(nullable = false, unique = true)
    private String email;

    private String firstName;
    private String lastName;

    @Enumerated(EnumType.STRING)
    private Role role;
    @Builder.Default
    private boolean enabled = true;
}
