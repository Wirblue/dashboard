import { Injectable } from '@angular/core';
import {Service} from '../_class/service';
import {GlobalVariable} from '../globals';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {LoginService} from './login.service';
import {AuthServiceDesc} from '../_class/auth-service-desc';
import {Observable} from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AuthorizationService {

  constructor(private http: HttpClient,
              private loginService: LoginService) { }

  private initHeader(): HttpHeaders {
    return new HttpHeaders({
      'Content-Type': 'application/json',
      'authorization': this.loginService.getToken()
    });
  }

  logout(service: Service): Observable<AuthServiceDesc> {
    return this.http.delete<AuthServiceDesc>(GlobalVariable.API_URL + '/authorizations/' + service.name, { headers: this.initHeader() });
  }
}
