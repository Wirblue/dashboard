import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders, HttpResponse, HttpParams} from '@angular/common/http';
import {catchError, map, tap} from 'rxjs/operators';
import { Observable, of} from 'rxjs';
import { GlobalVariable } from '../globals';
import {LoginRegister} from '../_class/login-register';
import {LoginLogin} from '../_class/login-login';
import { Router } from '@angular/router';

interface Post {
  title: string;
  body: string;
}

@Injectable({
  providedIn: 'root'
})
export class LoginService {

  constructor(private http: HttpClient, private router: Router) {}

  httpOptions = {
    headers: new HttpHeaders({
      'Content-Type': 'application/json',
      'observe': 'response'
    })
  };

  log = false;
  token: string;

  register(loginRegister: LoginRegister): void {
    this.http.post(GlobalVariable.BASE_API_URL + '/users/register', loginRegister, this.httpOptions)
      .pipe(catchError(this.handleError('register', [])))
      .subscribe(res => {
          console.log(res);
        }
      );
  }

  login(loginLogin: LoginLogin): void {
    this.http.post(GlobalVariable.BASE_API_URL + '/login', loginLogin,
      {
        observe: 'response',
      }
    )
      .subscribe(
        (res) => {
          this.token = res.headers.get('Authorization');
          this.log = true;
        }
      );
  }

  logout(): void {
    this.log = false;
    this.token = null;
  }

  isLog(): boolean {
    return this.log;
  }

  getToken(): string {
    return this.token;
  }

  /**
   * Handle Http operation that failed.
   * Let the app continue.
   * @param operation - name of the operation that failed
   * @param result - optional value to return as the observable result
   */
  private handleError<T> (operation = 'operation', result?: T) {
    return (error: any): Observable<T> => {
      console.error(error);
      return of(result as T);
    };
  }
}
