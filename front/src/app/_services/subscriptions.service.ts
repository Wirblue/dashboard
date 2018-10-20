import { Injectable } from '@angular/core';
import { Observable, of } from 'rxjs';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { LoginService } from './login.service';
import { GlobalVariable } from '../globals';
import { Widget } from '../_class/widget/widget';
import { WidgetDesc } from '../_class/widget/widget-desc';
import {IntervalService} from './interval.service';
import {AlertService} from './alert.service';

@Injectable({
  providedIn: 'root'
})
export class SubscriptionsService {

  constructor(private http: HttpClient,
              private loginService: LoginService,
              private intervaleService: IntervalService,
              private alertService: AlertService) {}

  private initHeader(): HttpHeaders {
    return new HttpHeaders({
      'Content-Type': 'application/json',
      'authorization': this.loginService.getToken()
    });
  }

  getSubscriptions() {
    return this.http.get<WidgetDesc[]>(GlobalVariable.API_URL + '/subscriptions', { headers: this.initHeader()});
  }
}
