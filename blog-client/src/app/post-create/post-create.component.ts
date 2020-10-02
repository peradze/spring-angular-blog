import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Category } from '../_models/category';
import { CategoryService } from '../_services/category.service';
import { PostService } from '../_services/post.service';
import { MatSnackBar } from '@angular/material/snack-bar';
import { PostCreate } from '../_models/post-create';

@Component({
  selector: 'app-post-create',
  templateUrl: './post-create.component.html',
  styleUrls: ['./post-create.component.scss'],
})
export class PostCreateComponent implements OnInit {
  blogForm = new FormGroup({
    title: new FormControl('', Validators.required),
    content: new FormControl('', Validators.required),
    categoryId: new FormControl('', Validators.required),
  });

  categories: Category[] = [];
  loading = false;

  constructor(
    private categoryService: CategoryService,
    private postService: PostService,
    private snackBar: MatSnackBar
  ) {
    this.categoryService.getAll().subscribe((data) => {
      this.categories = data;
    });
  }

  ngOnInit(): void {}

  categorySelection(categoryId: number): void {
    this.blogForm.patchValue({ categoryId });
  }

  submit(): void {
    this.loading = true;
    console.log(this.blogForm.value);
    this.postService
      .addPost(this.blogForm.value as PostCreate)
      .subscribe((data) => {
        this.loading = false;
        this.blogForm.reset();
        this.snackBar.open(data.message, 'Dismiss', {
          duration: 3000,
          horizontalPosition: 'end',
          verticalPosition: 'top',
          panelClass: 'notify-success',
        });
      }, error => {
        this.loading = false;
        this.snackBar.open(error.error.message, 'Dismiss', {
          duration: 3000,
          horizontalPosition: 'end',
          verticalPosition: 'top',
          panelClass: 'notify-error',
        });
      });
  }
}
