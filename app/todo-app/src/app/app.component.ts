import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { Todo } from './todo';
import { TodoService } from './todo.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {

  title = 'todo-app';

  todos: Todo[] = [];

  constructor(
    private service: TodoService
  ) { }

  ngOnInit(): void {
    this.service.listar()
      .subscribe(todos => this.todos = todos);
  }

  form: FormGroup = new FormGroup({
    description: new FormControl('')
  });

  submit() {
    const todo: Todo = { ...this.form.value };
    this.service.salvar(todo)
      .subscribe(todo => {
        this.todos.push(todo)
        this.form.reset();
      });
  }
}
