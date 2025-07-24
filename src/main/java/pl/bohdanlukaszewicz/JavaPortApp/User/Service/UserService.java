package pl.bohdanlukaszewicz.JavaPortApp.User.Service;


import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pl.bohdanlukaszewicz.JavaPortApp.User.DTO.UserDto;
import pl.bohdanlukaszewicz.JavaPortApp.User.Mapper.UserMapper;
import pl.bohdanlukaszewicz.JavaPortApp.User.Model.User;
import pl.bohdanlukaszewicz.JavaPortApp.User.Model.UserModel;
import pl.bohdanlukaszewicz.JavaPortApp.User.Repository.UserRepository;


import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService implements IUserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

//<editor-fold desc="Constructor">
    public UserService(UserRepository userRepository, UserMapper userMapper, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.passwordEncoder = passwordEncoder;
    }
//</editor-fold>
//<editor-fold desc="Register">
    public UserDto createUser(User user, String rawPassword) {

        String passwordHashed = passwordEncoder.encode(rawPassword);
        user.setPasswordHashed(passwordHashed);


        UserModel saved = userRepository.save(userMapper.toUserModel(user));

        return userMapper.toUserDto(userMapper.toUser(saved));
    }
//</editor-fold>
//<editor-fold desc="Get Users">
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
//</editor-fold>
//<editor-fold desc="Delete">
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
//</editor-fold>
//<editor-fold desc="Login">
    public Optional<UserDto> login(String username, String rawPassword) {
        return userRepository.findByUsername(username)
                .filter(user -> passwordEncoder.matches(rawPassword, user.getPasswordHashed()))
                .map(userMapper::toUser)
                .map(userMapper::toUserDto);
    }
//</editor-fold>
//<editor-fold desc="Change Password">

    public boolean changePassword(Long userId, String oldPassword, String newPassword) {
        Optional<UserModel> optionalUser = userRepository.findById(userId);
        if (optionalUser.isEmpty()) return false;

        UserModel user = optionalUser.get();
        if (!passwordEncoder.matches(oldPassword, user.getPasswordHashed())) {
            return false;
        }

        user.setPasswordHashed(passwordEncoder.encode(newPassword));
        userRepository.save(user);
        return true;
    }

//</editor-fold>
//<editor-fold desc="Update User Info">
    public Optional<UserDto> updateUserInfo(Long userId, String newFirstName, String newLastName) {
        return userRepository.findById(userId)
                .map(user -> {
                    user.setFirstName(newFirstName);
                    user.setLastName(newLastName);
                    return userRepository.save(user);
                })
                .map(userMapper::toUser)
                .map(userMapper::toUserDto);
    }
//</editor-fold>


}
