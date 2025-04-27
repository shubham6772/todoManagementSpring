package com.dg.todo.service;

import com.dg.todo.dto.TodoDto.TodoRequestDto;
import com.dg.todo.dto.TodoDto.TodoResponseDto;

public interface TodoService {
    TodoResponseDto createTodo(TodoRequestDto todo);
    TodoResponseDto updateTodo(TodoRequestDto todo);
    void deleteTodo(String todoId);
}
