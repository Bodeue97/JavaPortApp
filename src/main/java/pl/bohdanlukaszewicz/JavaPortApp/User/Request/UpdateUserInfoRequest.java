package pl.bohdanlukaszewicz.JavaPortApp.User.Request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UpdateUserInfoRequest {

    @NotBlank(message = "First name is required")
    private String firstName;

    @NotBlank(message = "Last name is required")
    private String lastName;
}
