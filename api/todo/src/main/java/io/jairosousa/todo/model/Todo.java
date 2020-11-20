package io.jairosousa.todo.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class Todo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String descriptions;

    @Column
    private Boolean done;

    @Column
    private LocalDateTime createDate;

    @Column
    private LocalDateTime doneDate;

}
