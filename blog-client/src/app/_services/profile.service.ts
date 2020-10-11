import { Injectable } from '@angular/core';
import { environment } from '../../environments/environment';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { ProfileInfo } from '../_models/profile-info';
import { map } from 'rxjs/operators';

@Injectable({
  providedIn: 'root',
})
export class ProfileService {
  baseUrl = environment.baseUrl + '/api/profile';

  constructor(private http: HttpClient) {}

  getProfileInfo(): Observable<ProfileInfo> {
    return this.http.get<ProfileInfo>(`${this.baseUrl}`);
  }

  updateProfile(model: ProfileInfo): Observable<any> {
    return this.http.post(`${this.baseUrl}`, model);
  }

  getFullName(): Observable<string> {
    return this.http.get<ProfileInfo>(`${this.baseUrl}`).pipe(
      map((value) => {
        let fullName: string;
        fullName = value.firstName != null ? value.firstName : '';
        fullName += ' ';
        fullName += value.lastName != null ? value.lastName : '';
        return fullName;
      })
    );
  }
}
