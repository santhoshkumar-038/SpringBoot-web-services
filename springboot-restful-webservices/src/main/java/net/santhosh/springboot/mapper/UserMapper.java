package net.santhosh.springboot.mapper;

import net.santhosh.springboot.dto.UserDto;
import net.santhosh.springboot.entity.User;

public class UserMapper {

    // Convert User JPA Entity into User DTO
    public static UserDto mapToUserDto(User user){
        UserDto userDto = new UserDto(
                user.getId(),
                user.getFirstName(),
                user.getLastName(),
                user.getEmail()
        );
        return userDto;
    }

    // Convert UserDTO into User JPA Entity
    public static User mapToUser(UserDto userDto){
        User user = new User(
                userDto.getId(),
                userDto.getFirstName(),
                userDto.getLastName(),
                userDto.getEmail()
        );
        return user;
    }
}
