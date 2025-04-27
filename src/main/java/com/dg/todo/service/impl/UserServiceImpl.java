package com.dg.todo.service.impl;

import com.dg.todo.dto.UserClientDto;
import com.dg.todo.dto.UserDto.UserRequestDto;
import com.dg.todo.dto.UserDto.UserResponseDto;
import com.dg.todo.entity.UserEntity;
import com.dg.todo.exceptionHandlers.UserAlreadyExistException;
import com.dg.todo.exceptionHandlers.UserNotFoundException;
import com.dg.todo.mapper.AutoUserMapper;
import com.dg.todo.respository.UserRepository;
import com.dg.todo.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private final AutoUserMapper mapper;

    @Override
    public UserResponseDto createUser(UserRequestDto user){
        Optional<UserEntity> existingUser = userRepository.findByEmail(user.getEmail());

        if(existingUser.isPresent()){
            throw new UserAlreadyExistException("user already exist with this email");
        }

        UserEntity mappedUser = mapper.mapToUserEntity(user);

        UUID uuid = UUID.randomUUID();
        String newUUID = uuid.toString();

        mappedUser.setId(newUUID);

        UserEntity savedUser = userRepository.save(mappedUser);
        return mapper.mapToUserClientDto(savedUser);
    }

    @Override
    public UserResponseDto updateUser(UserRequestDto user){
            String uuid = user.getId();

            Optional<UserEntity> existingUser = userRepository.findById(uuid);
            if(existingUser.isEmpty()){
                throw new UserNotFoundException("User not exist with this uuid");
            }

            UserEntity clientUser = mapper.mapToUserEntity(user);
            UserEntity finalExistingUser = existingUser.get();
            finalExistingUser.setName(clientUser.getName());
            finalExistingUser.setEmail(clientUser.getEmail());

            UserEntity updatedUser = userRepository.save(finalExistingUser);

            return mapper.mapToUserClientDto(updatedUser);
    }

    @Override
    public void deleteUser(String userId){
        Optional<UserEntity> existingUser = userRepository.findById(userId);

        if(existingUser.isEmpty()){
            throw new UserNotFoundException("User not exist with this id");
        }

        userRepository.delete(existingUser.get());
    }

    @Override
    public UserResponseDto getUser(String userId){
        Optional<UserEntity> existingUser = userRepository.findById(userId);

        if(existingUser.isEmpty()){
            throw new UserNotFoundException("User not exist with this id..");
        }
        return mapper.mapToUserClientDto(existingUser.get());

    }

    @Override
    public List<UserResponseDto> getAllUser(){
        List<UserEntity> savedUsers = userRepository.findAll();

        return savedUsers.stream().map(mapper::mapToUserClientDto).collect(Collectors.toList());
    }

}
