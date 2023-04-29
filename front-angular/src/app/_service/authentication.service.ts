import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class AuthenticationService {
  private apiUrl = 'http://localhost:8080/api/auth';

  constructor(private http: HttpClient) {}

  login(username: string, password: string): Observable<boolean> {
    const headers = new HttpHeaders({
      Authorization: 'Basic ' + btoa(username + ':' + password)
    });

    return this.http.get<any>(`${this.apiUrl}/login`, { headers }).pipe(
      map(response => {
        if (response) {
          localStorage.setItem('currentUser', JSON.stringify({ username, password }));
          return true;
        }
        return false;
      })
    );
  }

  logout(): void {
    localStorage.removeItem('currentUser');
  }

  isAuthenticated(): boolean {
    return !!localStorage.getItem('currentUser');
  }

  getAuthHeaders(): HttpHeaders | null {
    const currentUser = JSON.parse(localStorage.getItem('currentUser') || '{}');
    if (currentUser && currentUser.username && currentUser.password) {
      return new HttpHeaders({
        Authorization: 'Basic ' + btoa(currentUser.username + ':' + currentUser.password)
      });
    }
    return null;
  }
}