package net.santhosh.springboot.service;

import lombok.AllArgsConstructor;
import net.santhosh.springboot.dto.UserDto;
import net.santhosh.springboot.entity.User;
import net.santhosh.springboot.exception.EmailAlreadyExistsException;
import net.santhosh.springboot.exception.ResourceNotFoundException;
import net.santhosh.springboot.mapper.AutoUserMapper;
import net.santhosh.springboot.mapper.UserMapper;
import net.santhosh.springboot.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService{
    // we are using constructor based auto wiring
    private UserRepository userRepository;

    private ModelMapper modelMapper;

    @Override
    public UserDto createUser(UserDto userDto) {

        //  Convert UserDto into user jpa entity

        // User user = UserMapper.mapToUser(userDto);
//        User user = modelMapper.map(userDto, User.class);

        Optional<User> optionalUser = userRepository.findByEmail(userDto.getEmail());
        if(optionalUser.isPresent()){
            throw new EmailAlreadyExistsException("Email Already Exists for User");
        }

        User user = AutoUserMapper.MAPPER.mapToUser(userDto);

        User savedUser = userRepository.save(user);

        // Convert user JPA entity into UserDTO
        // UserDto savedUserDto = UserMapper.mapToUserDto(savedUser);
        // UserDto savedUserDto = modelMapper.map(savedUser, UserDto.class);
        UserDto savedUserDto = AutoUserMapper.MAPPER.mapToUserDto(savedUser);

        return savedUserDto;
    }

    @Override
    public UserDto getUserById(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(
                () ->  new ResourceNotFoundException("User", "id", userId)
        );
        //return UserMapper.mapToUserDto(user);
        // return modelMapper.map(user, UserDto.class);
        return AutoUserMapper.MAPPER.mapToUserDto(user);
    }

    @Override
    public List<UserDto> getAllUsers(){
        List<User> users = userRepository.findAll();
        // return users.stream().map(UserMapper::mapToUserDto).collect(Collectors.toList());
        // return users.stream().map((user) -> modelMapper.map(user, UserDto.class))
        //        .collect(Collectors.toList());
        return users.stream().map((user) -> AutoUserMapper.MAPPER.mapToUserDto(user))
                .collect(Collectors.toList());
    }

    @Override
    public UserDto updateUser(UserDto user) {
        User existingUser = userRepository.findById(user.getId()).orElseThrow(
                () -> new ResourceNotFoundException("User", "id", user.getId())
        );
        existingUser.setFirstName(user.getFirstName());
        existingUser.setLastName(user.getLastName());
        existingUser.setEmail(user.getEmail());
        User updatedUser = userRepository.save(existingUser);
        // return UserMapper.mapToUserDto(updatedUser);
        // return modelMapper.map(updatedUser, UserDto.class);
        return AutoUserMapper.MAPPER.mapToUserDto(updatedUser);
    }

    @Override
    public void deleteUser(Long userId) {
        User existingUser = userRepository.findById(userId).orElseThrow(
                () -> new ResourceNotFoundException("User", "id", userId)
        );
        userRepository.deleteById(userId);
    }
}
