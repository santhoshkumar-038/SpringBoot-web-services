package net.santhosh.springboot.service;

import net.santhosh.springboot.dto.UserDto;
import net.santhosh.springboot.entity.User;
import java.util.List;

public interface UserService {
    UserDto createUser(UserDto user);

    UserDto getUserById(Long userId);

    List<UserDto> getAllUsers();

    UserDto updateUser(UserDto user);

    void deleteUser(Long userId);
}
