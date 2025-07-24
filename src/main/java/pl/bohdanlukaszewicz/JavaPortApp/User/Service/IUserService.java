package pl.bohdanlukaszewicz.JavaPortApp.User.Service;

import org.springframework.stereotype.Service;
import pl.bohdanlukaszewicz.JavaPortApp.User.Model.User;
import pl.bohdanlukaszewicz.JavaPortApp.User.DTO.UserDto;

import java.util.List;
import java.util.Optional;
public interface IUserService {
    UserDto createUser(User user, String rawPassword);

    List<UserDto> getAllUsers();

    Optional<UserDto> getUserById(Long id);

    Optional<UserDto> getUserByUsername(String username);

    void deleteUser(Long id);

    Optional<UserDto> login(String username, String rawPassword);

    boolean changePassword(Long userId, String oldPassword, String newPassword);

    Optional<UserDto> updateUserInfo(Long userId, String newFirstName, String newLastName);
}
