import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { ProfileService } from '../_services/profile.service';
import { ProfileInfo } from '../_models/profile-info';
import { MatSnackBar } from '@angular/material/snack-bar';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.scss'],
})
export class ProfileComponent implements OnInit {
  profileForm = new FormGroup({
    firstName: new FormControl('', Validators.required),
    lastName: new FormControl('', Validators.required),
    email: new FormControl(''),
  });
  profileInfo: ProfileInfo;
  loading = false;

  constructor(
    private profileService: ProfileService,
    private snackBar: MatSnackBar
  ) {}

  ngOnInit(): void {
    this.profileService.getProfileInfo().subscribe((data) => {
      this.profileInfo = data;
      this.profileForm.patchValue({
        firstName: this.profileInfo.firstName,
        lastName: this.profileInfo.lastName,
        email: this.profileInfo.email,
      });
    });
  }

  submit(): void {
    this.loading = true;
    this.profileService
      .updateProfile(this.profileForm.value as ProfileInfo)
      .subscribe(
        (data) => {
          this.loading = false;
          this.snackBar.open(data.message, 'Dismiss', {
            duration: 3000,
            horizontalPosition: 'end',
            verticalPosition: 'top',
            panelClass: 'notify-success',
          });
        },
        (error) => {
          this.snackBar.open(error.error.message, 'Dismiss', {
            duration: 3000,
            horizontalPosition: 'end',
            verticalPosition: 'top',
            panelClass: 'notify-error',
          });
        }
      );
  }
}
