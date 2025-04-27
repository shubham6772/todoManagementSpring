package com.dg.todo.dto.TodoDto;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TodoRequestDto {

    private String id;

    @NotEmpty(message = "user_id must not be empty or null")
    private String user_id;

    @NotEmpty(message = "name must not be empty or null")
    private String title;

    @NotEmpty(message = "email must not be empty or null")
    private String description;

    private String completed;

}
