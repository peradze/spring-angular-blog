import { Component, OnInit } from '@angular/core';
import { ProfileService } from '../_services/profile.service';

@Component({
  selector: 'app-admin',
  templateUrl: 'admin.component.html',
  styleUrls: ['admin.component.scss'],
})
export class AdminComponent implements OnInit {
  fullName: string;

  constructor(private profileService: ProfileService) {}

  ngOnInit(): void {
    this.profileService
      .getFullName()
      .subscribe((data) => (this.fullName = data));
  }
}
