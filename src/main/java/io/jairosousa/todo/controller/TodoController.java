package io.jairosousa.todo.controller;

import io.jairosousa.todo.model.Todo;
import io.jairosousa.todo.model.TodoDTO;
import io.jairosousa.todo.model.mapper.TodoMapper;
import io.jairosousa.todo.repository.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/todos")
public class TodoController {

    @Autowired
    private TodoRepository repository;

    @PostMapping
    public TodoDTO save(@RequestBody Todo todo) {
        return TodoMapper.toDTO(repository.save(todo));
    }

    @GetMapping("{id}")
    public TodoDTO getById(@PathVariable Long id) {
        return repository.findById(id)
                .map(todo -> TodoMapper.toDTO(todo))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @GetMapping
    public List<TodoDTO> getAll() {
        Sort sort = Sort.by(Sort.Direction.ASC,"done")
                .and(Sort.by(Sort.Direction.DESC,"doneDate"));
        return repository.findAll(sort).stream()
                .map(todo -> TodoMapper.toDTO(todo))
                .collect(Collectors.toList());
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable Long id) {
        repository.deleteById(id);
    }

    @PatchMapping("{id}/done")
    public TodoDTO markAsDone(@PathVariable Long id) {
        return repository.findById(id)
                .map(todo -> {
                    todo.setDone(true);
                    todo.setDoneDate(LocalDateTime.now());
                    repository.save(todo);
                    return TodoMapper.toDTO(todo);
                }).orElse(null);
    }
}
