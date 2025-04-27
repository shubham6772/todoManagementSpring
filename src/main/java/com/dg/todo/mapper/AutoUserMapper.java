package com.dg.todo.mapper;

import com.dg.todo.dto.UserClientDto;
import com.dg.todo.dto.UserDto.UserRequestDto;
import com.dg.todo.dto.UserDto.UserResponseDto;
import com.dg.todo.entity.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface AutoUserMapper {
    AutoUserMapper Mapper = Mappers.getMapper(AutoUserMapper.class);

    UserEntity mapToUserEntity(UserRequestDto user);
    UserResponseDto mapToUserClientDto(UserEntity user);
}
