package com.dg.todo.dto.TodoDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TodoResponseDto {

    private String id;
    private String title;
    private String completed;
}
