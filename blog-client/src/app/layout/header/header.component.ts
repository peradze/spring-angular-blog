import { Component, OnInit } from '@angular/core';
import { AuthService } from '../../_services/auth.service';
import { ProfileService } from '../../_services/profile.service';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.scss'],
})
export class HeaderComponent implements OnInit {
  loggedIn: boolean;
  fullName: string;

  constructor(
    private authService: AuthService,
    private profileService: ProfileService
  ) {}

  ngOnInit(): void {
    this.loggedIn = this.authService.isAuthorized();
    this.profileService
      .getFullName()
      .subscribe((data) => (this.fullName = data));
  }
}
