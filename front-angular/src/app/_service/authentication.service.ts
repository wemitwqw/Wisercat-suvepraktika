import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { HttpClient, HttpErrorResponse, HttpHeaders } from '@angular/common/http';
import { BehaviorSubject, Observable, throwError } from 'rxjs';
import { catchError, map, tap } from 'rxjs/operators';

// import { environment } from '@environments/environment';
import { IUser } from '../_model/user';

@Injectable({ providedIn: 'root' })
export class AuthenticationService {
    private userSubject: BehaviorSubject<IUser | null>;
    public user: Observable<IUser | null>;

    constructor(
        private router: Router,
        private http: HttpClient
    ) {
        this.userSubject = new BehaviorSubject(JSON.parse(localStorage.getItem('user')!));
        this.user = this.userSubject.asObservable();
    }

    public get userValue() {
        return this.userSubject.value;
    }

    login(username: string, password: string) {
        
        const httpOptions = {
            headers: new HttpHeaders({
              'Content-Type':  'application/json'
            })
        };
        // this.router.navigate(['/login']);
        
        let request = this.http.post<any>(`http://localhost:8080/api/auth/login`, JSON.stringify({ username, password }), httpOptions).pipe(
            catchError(error => {
              const statusCode = error.status;

              return throwError(error);
            })
          )
          .subscribe(response => {
            console.log(response.json());
          });

        // .pipe(map(user => {
        //     user = window.btoa(username + ':' + password);
        //     localStorage.setItem('user', JSON.stringify(user));
        //     // this.userSubject.next(user);
        //     // return user;
        // }));
    }

    logout() {
        // remove user from local storage to log user out
        localStorage.removeItem('user');
        this.userSubject.next(null);
        this.router.navigate(['/login']);
    }
}