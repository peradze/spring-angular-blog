import { Injectable } from '@angular/core';
import { environment } from '../../environments/environment';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import { JwtHelperService } from '@auth0/angular-jwt';
import { User } from '../_models/user';

@Injectable({
  providedIn: 'root',
})
export class AuthService {
  baseUrl = environment.baseUrl + '/api/auth';
  jwtHelper = new JwtHelperService();
  user: User = new User();

  constructor(private http: HttpClient) {}

  login(model: any): Observable<any> {
    return this.http.post(`${this.baseUrl}/login`, model).pipe(
      map((res: any) => {
        localStorage.setItem('currentUser', JSON.stringify(res));
        const decodedToken = this.jwtHelper.decodeToken(res.token);
        this.user.email = decodedToken.sub;
        this.user.roles = res.roles;
      })
    );
  }

  register(model: any): Observable<any> {
    return this.http.post(`${this.baseUrl}/signup`, model);
  }

  logout(): void {
    this.user = new User();
    localStorage.removeItem('currentUser');
  }

  isAuthorized(): boolean {
    return localStorage.getItem('currentUser') != null;
  }

  hasAdminRole(): boolean {
    const user: User = JSON.parse(localStorage.getItem('currentUser'));
    return user.roles.findIndex(value => value === 'ROLE_ADMIN') !== -1;
  }

  getAuthUser(): User | null {
    return this.isAuthorized() ? this.user : null;
  }
}
