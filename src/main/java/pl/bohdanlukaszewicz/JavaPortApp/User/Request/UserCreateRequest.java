package pl.bohdanlukaszewicz.JavaPortApp.User.Request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.bohdanlukaszewicz.JavaPortApp.Enums.Role;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserCreateRequest {
    @Size(min = 3, message = "Username must be at least 3 characters")
    private String username;

    @Email(message = "Email should be valid")
    private String email;

    private String firstName;

    private String lastName;

    private Role role;

    private boolean enabled;

    @Size(min = 6, message = "Password must be at least 6 characters")
    private String rawPassword;
}
