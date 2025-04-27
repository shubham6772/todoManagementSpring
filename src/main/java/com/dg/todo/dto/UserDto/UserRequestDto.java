package com.dg.todo.dto.UserDto;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRequestDto {

    private String id;

    @NotEmpty(message = "name must not be null or empty")
    private String name;

    @NotEmpty(message = "email must not be null or empty")
    private String email;

}
