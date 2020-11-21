package io.jairosousa.todo.controller;

import io.jairosousa.todo.model.Todo;
import io.jairosousa.todo.repository.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

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
        return repository.findAll();
    }
}
