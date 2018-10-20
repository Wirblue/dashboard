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
import {User} from '../_class/user';
import {IntervalService} from './interval.service';
import {CookieService} from 'ngx-cookie-service';

@Injectable({
  providedIn: 'root'
})
export class LoginService {

  constructor(private http: HttpClient,
              private alertService: AlertService,
              private intervalService: IntervalService,
              private cookieService: CookieService) {
  }

  log = false;
  token: string;

  register(loginRegister: LoginRegister): Observable<HttpResponse<Object>> {
    return this.http.post<any>(GlobalVariable.API_URL + '/users/register', loginRegister, { observe: 'response' });
  }

  login(loginLogin: LoginLogin): Observable<any | HttpResponse<Object>> {
    return this.http.post<any>(GlobalVariable.API_URL + '/users/login', loginLogin, { observe: 'response' });
  }

  private initHeader(): HttpHeaders {
    return new HttpHeaders({
      'Content-Type': 'application/json',
      'authorization': this.getToken()
    });
  }

  me() {
    return this.http.get<User>(GlobalVariable.API_URL + '/users/me', { headers: this.initHeader() });
  }

  logout(): void {
    this.intervalService.stopAll();
    this.log = false;
    this.cookieService.delete(GlobalVariable.COOKIE_NAME);
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
    this.alertService.addAlert(error.error.error, error.error.message);
  }
}
