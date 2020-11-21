import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from '../environments/environment';
import { Todo } from './todo';

@Injectable({
  providedIn: 'root'
})
export class TodoService {

  apiUrl = environment.url;

  constructor(
    private http: HttpClient
  ) { }

  salvar(todo: Todo): Observable<Todo> {
    return this.http.post<Todo>(`${this.apiUrl}/api/todos`, todo);
  }

  listar(): Observable<Todo[]> {
    return this.http.get<Todo[]>(`${this.apiUrl}/api/todos`);
  }
}
