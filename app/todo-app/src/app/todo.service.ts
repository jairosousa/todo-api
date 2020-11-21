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

  endpoint = environment.endpointTodo;

  constructor(
    private http: HttpClient
  ) { }

  salvar(todo: Todo): Observable<Todo> {
    return this.http.post<Todo>(`${this.apiUrl}/${this.endpoint}`, todo);
  }

  listar(): Observable<Todo[]> {
    return this.http.get<Todo[]>(`${this.apiUrl}/${this.endpoint}`);
  }
  deletar(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${this.endpoint}/${id}`);
  }

  marcarComoConcluido(id: number): Observable<Todo> {
    return this.http.patch<Todo>(`${this.apiUrl}/${this.endpoint}/${id}/done`, {});
  }
}
