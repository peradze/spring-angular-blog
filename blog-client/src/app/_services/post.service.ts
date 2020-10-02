import { Injectable } from '@angular/core';
import {environment} from '../../environments/environment';
import {HttpClient} from '@angular/common/http';
import {PostCreate} from '../_models/post-create';
import {Observable} from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class PostService {
  baseUrl = environment.baseUrl + '/api/posts';

  constructor(private http: HttpClient) { }

  addPost(post: PostCreate): Observable<any> {
    return this.http.post(`${this.baseUrl}`, post);
  }
}
