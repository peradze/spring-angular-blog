import { Component, OnInit } from '@angular/core';
import { PostService } from '../_services/post.service';
import { PostList } from '../_models/post-list';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss'],
})
export class HomeComponent implements OnInit {
  constructor(private postService: PostService) {}
  posts: PostList[] = [];

  ngOnInit(): void {
    this.postService.getPosts().subscribe((data) => {
      this.posts = data;
    });
  }
}
