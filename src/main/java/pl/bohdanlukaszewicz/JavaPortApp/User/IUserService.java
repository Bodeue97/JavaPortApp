package pl.bohdanlukaszewicz.JavaPortApp.User;

import java.util.List;
import java.util.Optional;

public interface IUserService {
    UserDto createUser(User user, String rawPassword);
    List<UserDto> getAllUsers();
    Optional<UserDto> getUserById(Long id);
    Optional<UserDto> getUserByUsername(String username);
    void deleteUser(Long id);
}