import { Component, OnInit } from '@angular/core';
import { PostService } from '../_services/post.service';
import { PostList } from '../_models/post-list';
import { switchMap } from 'rxjs/operators';
import { PostCategoryCount } from '../_models/post-category-count';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss'],
})
export class HomeComponent implements OnInit {
  constructor(private postService: PostService) {}
  posts: PostList[] = [];
  postsAll: PostList[] = [];
  postCount: PostCategoryCount[] = [];
  selectedCategory = 'All';

  ngOnInit(): void {
    this.postService
      .getPosts()
      .pipe(
        switchMap((posts) => {
          this.posts = posts;
          this.postsAll = posts;
          return this.postService.getPostCountByCategory();
        })
      )
      .subscribe((data) => {
        this.postCount = data;
      });
  }

  postsByCategory(categoryName: string): void {
    this.selectedCategory = categoryName;
    if (categoryName === 'All') {
      this.posts = this.postsAll;
    } else {
      this.posts = this.postsAll.filter(value => value.category === categoryName);
    }
  }
}
