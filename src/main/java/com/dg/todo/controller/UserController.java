package com.dg.todo.controller;

import com.dg.todo.dto.UserDto.UserRequestDto;
import com.dg.todo.dto.UserDto.UserResponseDto;
import com.dg.todo.service.UserService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("api/v1/user")
public class UserController {

    private UserService userService;

    @PostMapping
    public ResponseEntity<UserResponseDto> createUser(@RequestBody @Valid  UserRequestDto userData){

        UserResponseDto savedUser = userService.createUser(userData);

        return new ResponseEntity<>(savedUser, HttpStatus.CREATED);

    }

    @PatchMapping
    public ResponseEntity<UserResponseDto> updateUser(@RequestBody @Valid UserRequestDto userData){

        UserResponseDto savedUser = userService.updateUser(userData);

        return new ResponseEntity<>(savedUser, HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Boolean> deleteUser(@PathVariable("id") String userId){
         userService.deleteUser(userId);
         return new ResponseEntity<>(true, HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<UserResponseDto> getUser(@PathVariable("id") @Valid String userId){
         UserResponseDto savedUser = userService.getUser(userId);

         return new ResponseEntity<>(savedUser, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<UserResponseDto>> getAllUser(){
        List<UserResponseDto> allUsers = userService.getAllUser();

        return new ResponseEntity<>(allUsers, HttpStatus.OK);
    }
    
}
