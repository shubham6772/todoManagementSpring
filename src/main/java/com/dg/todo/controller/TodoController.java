package com.dg.todo.controller;

import com.dg.todo.dto.TodoDto.TodoRequestDto;
import com.dg.todo.dto.TodoDto.TodoResponseDto;
import com.dg.todo.service.TodoService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


@RestController
@AllArgsConstructor
@RequestMapping("api/v1/todo")
public class TodoController {

    private TodoService todoService;

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<TodoResponseDto> createTodo(@RequestBody @Valid TodoRequestDto todoData){
        TodoResponseDto savedTodo = todoService.createTodo(todoData);
        return new ResponseEntity<>(savedTodo, HttpStatus.CREATED);
    }

    @PatchMapping
    public ResponseEntity<TodoResponseDto> updateTodo(@RequestBody @Valid TodoRequestDto todoData){
        TodoResponseDto savedTodo = todoService.updateTodo(todoData);
        return new ResponseEntity<>(savedTodo, HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Boolean> deleteTodo(@PathVariable @Valid String id){
        todoService.deleteTodo(id);
        return new ResponseEntity<>(true, HttpStatus.OK);
    }



}
