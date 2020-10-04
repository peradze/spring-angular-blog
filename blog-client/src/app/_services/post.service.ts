import { Injectable } from '@angular/core';
import { environment } from '../../environments/environment';
import { HttpClient } from '@angular/common/http';
import { PostCreate } from '../_models/post-create';
import { Observable } from 'rxjs';
import { PostList } from '../_models/post-list';
import { PostDetail } from '../_models/post-detail';

@Injectable({
  providedIn: 'root',
})
export class PostService {
  baseUrl = environment.baseUrl + '/api/posts';

  constructor(private http: HttpClient) {}

  addPost(post: PostCreate): Observable<any> {
    return this.http.post(`${this.baseUrl}`, post);
  }

  getPosts(): Observable<PostList[]> {
    return this.http.get<PostList[]>(`${this.baseUrl}`);
  }

  getPostDetail(id): Observable<PostDetail> {
    return this.http.get<PostDetail>(`${this.baseUrl}/${id}`);
  }
}
