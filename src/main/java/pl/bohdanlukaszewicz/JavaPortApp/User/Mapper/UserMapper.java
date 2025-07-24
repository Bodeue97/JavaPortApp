package pl.bohdanlukaszewicz.JavaPortApp.User.Mapper;

import org.springframework.stereotype.Component;
import pl.bohdanlukaszewicz.JavaPortApp.User.DTO.UserDto;
import pl.bohdanlukaszewicz.JavaPortApp.User.Model.User;
import pl.bohdanlukaszewicz.JavaPortApp.User.Model.UserModel;

@Component
public class UserMapper {

    public User toUser(UserModel model) {
        if (model == null) return null;
        return User.builder()
                .id(model.getId())
                .username(model.getUsername())
                .passwordHashed(model.getPasswordHashed())
                .email(model.getEmail())
                .firstName(model.getFirstName())
                .lastName(model.getLastName())
                .role(model.getRole())
                .enabled(model.isEnabled())
                .build();
    }

    public UserModel toUserModel(User user) {
        if (user == null) return null;
        return UserModel.builder()
                .id(user.getId())
                .username(user.getUsername())
                .passwordHashed(user.getPasswordHashed())
                .email(user.getEmail())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .role(user.getRole())
                .enabled(user.isEnabled())
                .build();
    }

    public UserDto toUserDto(User user) {
        if (user == null) return null;
        return UserDto.builder()
                .id(user.getId())
                .username(user.getUsername())
                .email(user.getEmail())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .role(user.getRole())
                .enabled(user.isEnabled())
                .build();
    }

    public User toUser(UserDto dto) {
        if (dto == null) return null;
        return User.builder()
                .id(dto.getId())
                .username(dto.getUsername())
                .email(dto.getEmail())
                .firstName(dto.getFirstName())
                .lastName(dto.getLastName())
                .role(dto.getRole())
                .enabled(dto.isEnabled())
                .build();
    }
}
