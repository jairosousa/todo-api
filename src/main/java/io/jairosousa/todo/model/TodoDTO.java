package io.jairosousa.todo.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class TodoDTO {

    private Long id;

    private String description;

    private Boolean done;

    private String createDate;

    private String doneDate;

}
