package com.dg.todo.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserClientDto {

        private String id;

        @NotEmpty(message = "name must not be empty or null")
        private String name;

        @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
        @NotEmpty(message = "email must not be empty or null")
        @Email(message = "must be a valid email")
        private String email;

}
