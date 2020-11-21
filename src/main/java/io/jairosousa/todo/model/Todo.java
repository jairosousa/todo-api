package io.jairosousa.todo.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.OffsetDateTime;

@Entity
@Getter
@Setter
public class Todo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String description;

    @Column
    private Boolean done;

    @Column
    private OffsetDateTime createDate;

    @Column
    private OffsetDateTime doneDate;

    @PrePersist
    public void beforeSave() {
        setCreateDate(OffsetDateTime.now());
    }

}
