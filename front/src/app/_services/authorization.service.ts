import { Injectable } from '@angular/core';
import {Service} from '../_class/service';
import {GlobalVariable} from '../globals';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {LoginService} from './login.service';

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

  logout(service: Service) {
    return this.http.delete(GlobalVariable.API_URL + '/authorizations/' + service.name, { headers: this.initHeader() });
  }
}
