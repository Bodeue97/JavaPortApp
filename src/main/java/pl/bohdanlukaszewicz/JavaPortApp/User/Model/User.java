package pl.bohdanlukaszewicz.JavaPortApp.User.Model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.*;
import pl.bohdanlukaszewicz.JavaPortApp.Enums.Role;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {
    private Long id;
    @Size(min = 3, message = "Username must be at least 3 characters")
    private String username;
    @Email(message = "Email should be valid")
    private String email;
    private String firstName;
    private String lastName;
    private Role role;
    private boolean enabled;
    private String passwordHashed;
}
