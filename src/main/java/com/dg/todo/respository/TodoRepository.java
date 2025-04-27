package com.dg.todo.respository;

import com.dg.todo.entity.TodoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TodoRepository extends JpaRepository<TodoEntity, String> {
}
