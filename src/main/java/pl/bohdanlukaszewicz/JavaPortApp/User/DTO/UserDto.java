package pl.bohdanlukaszewicz.JavaPortApp.User.DTO;

import lombok.*;
import pl.bohdanlukaszewicz.JavaPortApp.Enums.Role;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDto {
    private Long id;
    private String username;
    private String email;
    private String firstName;
    private String lastName;
    private Role role;
    private boolean enabled;
}
