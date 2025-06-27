package pl.bohdanlukaszewicz.JavaPortApp.User;

import lombok.*;
import pl.bohdanlukaszewicz.JavaPortApp.Enums.Role;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {
    private Long id;
    private String username;
    private String email;
    private String firstName;
    private String lastName;
    private Role role;
    private boolean enabled;
    private String passwordHashed;




}
