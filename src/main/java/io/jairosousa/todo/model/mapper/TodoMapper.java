package io.jairosousa.todo.model.mapper;

import io.jairosousa.todo.model.Todo;
import io.jairosousa.todo.model.TodoDTO;
import org.springframework.util.StringUtils;

import java.time.format.DateTimeFormatter;
import java.util.Locale;


public class TodoMapper {

    static Locale locale = new Locale("pt", "BR");
    static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm", locale);

    public static TodoDTO toDTO(Todo todo) {
        return TodoDTO.builder()
                .description(todo.getDescription())
                .done(todo.getDone())
                .id(todo.getId())
                .createDate(todo.getCreateDate().format(formatter))
                .doneDate(isDone(todo))
                .build();

    }

    private static String isDone(Todo todo) {
        return !StringUtils.isEmpty(todo.getDoneDate()) ?
                todo.getDoneDate().format(formatter)
                : null;
    }
}
