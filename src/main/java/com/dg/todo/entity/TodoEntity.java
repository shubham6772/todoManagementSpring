package com.dg.todo.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "todos")
public class TodoEntity {

    @Id
    private String id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String description;

    @Column(columnDefinition = "TINYINT(1)", nullable = false)
    private boolean completed;

    @Column(name = "user_id", nullable = false)
    private String user_id;
}
