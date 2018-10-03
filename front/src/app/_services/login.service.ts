import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpResponse, HttpParams } from '@angular/common/http';
import { catchError, map, tap } from 'rxjs/operators';
import { Observable, of } from 'rxjs';
import { GlobalVariable } from '../globals';
import { LoginRegister } from '../_class/login-register';
import { LoginLogin } from '../_class/login-login';
import { Router } from '@angular/router';
import {AlertService} from './alert.service';
import {Alert} from '../_class/alert';

@Injectable({
  providedIn: 'root'
})
export class LoginService {

  constructor(private http: HttpClient, private alertService: AlertService) {}

  log = false;
  token: string;

  register(loginRegister: LoginRegister): Observable<HttpResponse<Object>> {
    return this.http.post<any>(GlobalVariable.BASE_API_URL + '/users/register', loginRegister, { observe: 'response' });
  }

  login(loginLogin: LoginLogin): Observable<any | HttpResponse<Object>> {
    return this.http.post<any>(GlobalVariable.BASE_API_URL + '/login', loginLogin, { observe: 'response' });
  }

  logout(): void {
    this.log = false;
    this.token = null;
  }

  setToken(token: string) {
    this.token = token;
    this.log = true;
  }

  isLog(): boolean {
    return this.log;
  }

  getToken(): string {
    return this.token;
  }

  handleError(error): void {
    this.alertService.addAlert(new Alert(error.error.error, error.error.message, 'alert-danger'));
  }
}
