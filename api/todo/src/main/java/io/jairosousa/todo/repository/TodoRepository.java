package io.jairosousa.todo.repository;

import io.jairosousa.todo.model.Todo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TodoRepository extends JpaRepository<Todo, Long> {
}
