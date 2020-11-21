package io.jairosousa.todo.controller;

import io.jairosousa.todo.model.Todo;
import io.jairosousa.todo.repository.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/todos")
public class TodoController {

    @Autowired
    private TodoRepository repository;

    @PostMapping
    public Todo save(@RequestBody Todo todo) {
        return repository.save(todo);
    }

    @GetMapping("{id}")
    public Todo getById(@PathVariable Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @GetMapping
    public List<Todo> getAll() {
        Sort sort = Sort.by(Sort.Direction.ASC,"done")
                .and(Sort.by(Sort.Direction.DESC,"doneDate"));
        return repository.findAll(sort);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable Long id) {
        repository.deleteById(id);
    }

    @PatchMapping("{id}/done")
    public Todo markAsDone(@PathVariable Long id) {
        return repository.findById(id)
                .map(todo -> {
                    todo.setDone(true);
                    todo.setDoneDate(LocalDateTime.now());
                    repository.save(todo);
                    return todo;
                }).orElse(null);
    }
}
