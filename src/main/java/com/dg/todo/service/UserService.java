package com.dg.todo.service;

import com.dg.todo.dto.UserClientDto;
import com.dg.todo.dto.UserDto.UserRequestDto;
import com.dg.todo.dto.UserDto.UserResponseDto;

import java.util.List;

public interface UserService {

    UserResponseDto createUser(UserRequestDto user);
    UserResponseDto updateUser(UserRequestDto user);
    void deleteUser(String userId);
    UserResponseDto getUser(String userId);
    List<UserResponseDto> getAllUser();
}
