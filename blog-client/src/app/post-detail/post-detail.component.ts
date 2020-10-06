import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { PostService } from '../_services/post.service';
import { PostDetail } from '../_models/post-detail';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { CommentService } from '../_services/comment.service';
import { CommentCreate } from '../_models/comment-create';
import { CommentDetail } from '../_models/comment-detail';
import { AuthService } from '../_services/auth.service';

@Component({
  selector: 'app-post-detail',
  templateUrl: './post-detail.component.html',
  styleUrls: ['./post-detail.component.scss'],
})
export class PostDetailComponent implements OnInit {
  commentForm = new FormGroup({
    postId: new FormControl(),
    comment: new FormControl('', Validators.required),
  });

  post: PostDetail;
  comments: CommentDetail[] = [];
  commentsChunk: CommentDetail[];
  commentToDisplay = 5;
  moreComment = false;
  loading = false;
  postId;
  loggedIn = false;

  constructor(
    private route: ActivatedRoute,
    private postService: PostService,
    private commentService: CommentService,
    private authService: AuthService
  ) {}

  ngOnInit(): void {
    this.loggedIn = this.authService.isAuthorized();
    this.route.paramMap.subscribe((params) => {
      this.postService.getPostDetail(params.get('id')).subscribe((data) => {
        this.postId = Number(params.get('id'));
        this.post = data;
        this.comments = this.post.comments;
        this.comments.reverse();
        if (this.comments.length > this.commentToDisplay) {
          this.commentsChunk = this.comments.slice(0, this.commentToDisplay);
          this.moreComment = true;
        } else {
          this.commentsChunk = this.comments.slice(0, this.comments.length);
          this.moreComment = false;
        }
        this.commentForm.patchValue({ postId: Number(params.get('id')) });
      });
    });
  }

  submit(): void {
    this.loading = true;
    this.commentService
      .addComment(this.commentForm.value as CommentCreate)
      .subscribe((data) => {
        this.loading = false;
        this.postService.getPostDetail(this.postId).subscribe((res) => {
          this.comments = res.comments;
          this.commentsChunk.unshift(this.comments[this.comments.length - 1]);
          this.comments.reverse();
        });
      });
  }

  moreComments(): void {
    if (this.comments.length > this.commentToDisplay) {
      this.commentToDisplay += this.commentToDisplay;
      this.moreComment = true;
      this.commentsChunk = this.comments.slice(0, this.commentToDisplay);
      if (this.comments.length < this.commentToDisplay) {
        this.moreComment = false;
      }
    }
  }
}
