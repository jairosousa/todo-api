import { Component } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { Todo } from './todo';
import { TodoService } from './todo.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {

  title = 'todo-app';

  todos: Todo[] = [];

  constructor(
    private service: TodoService
  ) { }

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
