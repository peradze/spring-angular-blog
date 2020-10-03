import { Injectable } from '@angular/core';
import { environment } from '../../environments/environment';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { ProfileInfo } from '../_models/profile-info';

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
}
