package pl.bohdanlukaszewicz.JavaPortApp.User.Controller;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.bohdanlukaszewicz.JavaPortApp.User.Request.ChangePasswordRequest;
import pl.bohdanlukaszewicz.JavaPortApp.User.Request.LoginRequest;
import pl.bohdanlukaszewicz.JavaPortApp.User.Request.UpdateUserInfoRequest;
import pl.bohdanlukaszewicz.JavaPortApp.User.Request.UserCreateRequest;
import pl.bohdanlukaszewicz.JavaPortApp.User.Service.IUserService;
import pl.bohdanlukaszewicz.JavaPortApp.User.Model.User;
import pl.bohdanlukaszewicz.JavaPortApp.User.DTO.UserDto;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private final IUserService _userService;

    public UserController(IUserService userService) {
        this._userService = userService;
    }

// <editor-fold desc="Create">
    @PostMapping
    public ResponseEntity<UserDto> createUser(@RequestBody @Valid UserCreateRequest request) {
        User user = User.builder()
                .username(request.getUsername())
                .email(request.getEmail())
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .role(request.getRole())
                .enabled(request.isEnabled())
                .build();

        UserDto createdUser = _userService.createUser(user, request.getRawPassword());
        return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
    }


    // <editor-fold desc="Get Users">
    @GetMapping
    public ResponseEntity<List<UserDto>> getAllUsers() {
        List<UserDto> users = _userService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable Long id) {
        Optional<UserDto> userOpt = _userService.getUserById(id);
        return userOpt.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }


    @GetMapping("/by-username/{username}")
    public ResponseEntity<UserDto> getUserByUsername(@PathVariable String username) {
        Optional<UserDto> userOpt = _userService.getUserByUsername(username);
        return userOpt.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // <editor-fold desc="Delete">
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        _userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

    // <editor-fold desc="Login">
    @PostMapping("/login")
    public ResponseEntity<UserDto> login(@RequestBody LoginRequest request) {
        Optional<UserDto> userOpt = _userService.login(request.getUsername(), request.getPassword());
        return userOpt.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.UNAUTHORIZED).build());
    }
    // </editor-fold>

    // <editor-fold desc="Change Password">
    @PostMapping("/{id}/change-password")
    public ResponseEntity<Void> changePassword(
            @PathVariable Long id,
            @RequestBody ChangePasswordRequest request) {

        boolean success = _userService.changePassword(id, request.getOldPassword(), request.getNewPassword());
        return success ? ResponseEntity.noContent().build() : ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }
    // </editor-fold>

    // <editor-fold desc="Update User Info">
    @PutMapping("/{id}/update-info")
    public ResponseEntity<UserDto> updateUserInfo(
            @PathVariable Long id,
            @RequestBody UpdateUserInfoRequest request) {

        Optional<UserDto> updatedUser = _userService.updateUserInfo(id, request.getFirstName(), request.getLastName());
        return updatedUser.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
