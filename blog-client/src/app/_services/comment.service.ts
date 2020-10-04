import { Injectable } from '@angular/core';
import { environment } from '../../environments/environment';
import { HttpClient } from '@angular/common/http';
import { CommentCreate } from '../_models/comment-create';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class CommentService {
  baseUrl = environment.baseUrl + '/api/comments';

  constructor(private http: HttpClient) {}

  addComment(comment: CommentCreate): Observable<any> {
    return this.http.post(`${this.baseUrl}`, comment);
  }
}
