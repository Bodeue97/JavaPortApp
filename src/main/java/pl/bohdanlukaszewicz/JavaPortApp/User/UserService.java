package pl.bohdanlukaszewicz.JavaPortApp.User;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, UserMapper userMapper, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.passwordEncoder = passwordEncoder;
    }

    public UserDto createUser(User user, String rawPassword) {

        String passwordHashed = passwordEncoder.encode(rawPassword);
        user.setPasswordHashed(passwordHashed);


        UserModel saved = userRepository.save(userMapper.toUserModel(user));

        // Return DTO
        return userMapper.toUserDto(userMapper.toUser(saved));
    }

    public List<UserDto> getAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(userMapper::toUser)
                .map(userMapper::toUserDto)
                .collect(Collectors.toList());
    }

    public Optional<UserDto> getUserById(Long id) {
        return userRepository.findById(id)
                .map(userMapper::toUser)
                .map(userMapper::toUserDto);
    }

    public Optional<UserDto> getUserByUsername(String username) {
        return userRepository.findByUsername(username)
                .map(userMapper::toUser)
                .map(userMapper::toUserDto);
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    // You can add more methods for updating users, validating passwords, etc.
}
