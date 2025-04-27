package com.dg.todo.mapper;

import com.dg.todo.dto.TodoDto.TodoRequestDto;
import com.dg.todo.dto.TodoDto.TodoResponseDto;
import com.dg.todo.entity.TodoEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface AutoTodoMapper {
    AutoTodoMapper Mapper = Mappers.getMapper(AutoTodoMapper.class);

    TodoResponseDto mapToClientDto(TodoEntity todo);

    TodoEntity mapToTodoEntity(TodoRequestDto toDoClient);

}
