package com.dg.todo.service.impl;

import com.dg.todo.dto.TodoDto.TodoRequestDto;
import com.dg.todo.dto.TodoDto.TodoResponseDto;
import com.dg.todo.entity.TodoEntity;
import com.dg.todo.entity.UserEntity;
import com.dg.todo.exceptionHandlers.TodoNotFoundException;
import com.dg.todo.exceptionHandlers.UserNotFoundException;
import com.dg.todo.mapper.AutoTodoMapper;
import com.dg.todo.respository.TodoRepository;
import com.dg.todo.respository.UserRepository;
import com.dg.todo.service.TodoService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@AllArgsConstructor
@Service
public class TodoServiceImpl implements TodoService {

    private TodoRepository todoRepository;
    private UserRepository userRepository;
    private final AutoTodoMapper mapper;

    @Override
    public TodoResponseDto createTodo(TodoRequestDto todo) {

        UUID todoUUID = UUID.randomUUID();
        String newTodoUUID = todoUUID.toString();

        TodoEntity mappedTodo = mapper.mapToTodoEntity(todo);

        mappedTodo.setId(newTodoUUID);

        Optional<UserEntity> existingUser = userRepository.findById(todo.getUser_id());

        if (existingUser.isEmpty()) {
            throw new UserNotFoundException("user not exist with this uuid");
        }

        UserEntity foundUser = existingUser.get();
        mappedTodo.setUser_id(foundUser.getId());
        userRepository.save(foundUser);

        TodoEntity savedTodo = todoRepository.save(mappedTodo);
        return mapper.mapToClientDto(savedTodo);

    }

    @Override
    public TodoResponseDto updateTodo(TodoRequestDto todo) {
        String userId = todo.getUser_id();

        Optional<UserEntity> existingUser = userRepository.findById(userId);

        if(existingUser.isEmpty()){
            throw new UserNotFoundException("no user exist with this user id");
        }

        Optional<TodoEntity> existingTodo = todoRepository.findById(todo.getId());
        if(existingTodo.isEmpty()){
            throw new TodoNotFoundException("no todo exist with this id");
        }

        TodoEntity savedTodo = existingTodo.get();
        savedTodo.setTitle(todo.getTitle());
        savedTodo.setDescription(todo.getDescription());
        todoRepository.save(savedTodo);
        return mapper.mapToClientDto(savedTodo);
    }

    @Override
    public void deleteTodo(String todoId) {
        Optional<TodoEntity> savedTodo = todoRepository.findById(todoId);

        if(savedTodo.isEmpty()){
            throw new TodoNotFoundException("no todo exist with this id");
        }

        todoRepository.delete(savedTodo.get());

    }


}
