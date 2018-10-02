import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders, HttpResponse, HttpParams} from '@angular/common/http';
import {catchError, map, tap} from 'rxjs/operators';
import { Observable, of} from 'rxjs';
import { GlobalVariable } from '../globals';
import {LoginRegister} from '../_class/login-register';
import {LoginLogin} from '../_class/login-login';

interface Post {
  title: string;
  body: string;
}

@Injectable({
  providedIn: 'root'
})
export class LoginService {

  constructor(private http: HttpClient) {}

  httpOptions = {
    headers: new HttpHeaders({
      'Content-Type': 'application/json',
      'observe': 'response'
    })
  };

  token: string;

  register(loginRegister: LoginRegister): void {
    this.http.post(GlobalVariable.BASE_API_URL + '/users/register', loginRegister, this.httpOptions)
      .pipe(
        catchError(this.handleError('register', []))
      ).subscribe(res => {console.log(res.json(à))});
  }

  login(loginLogin: LoginLogin): void {
    this.http.post(GlobalVariable.BASE_API_URL + '/login', loginLogin)
      .subscribe((res) => {
        console.log(res);
      });
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
