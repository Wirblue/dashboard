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

  private _widgets: WidgetDesc[] = [];

  private initHeader(): HttpHeaders {
    return new HttpHeaders({
      'Content-Type': 'application/json',
      'authorization': this.loginService.getToken()
    });
  }


  get widgets(): WidgetDesc[] {
    return this._widgets;
  }

  remove(id: number): void {
    const pos = this._widgets.map(x => {
      return x.id;
    }).indexOf(id);

    this._widgets.splice(pos, 1);
  }

  add(widget: WidgetDesc) {
    this._widgets.push(widget);
  }

  refreshWidgets() {
    this.intervaleService.stopAll();
    this.getSubscriptions().subscribe(
      data => this._widgets = data,
      error => this.alertService.addAlert('refreshWidgets', error.error.message)
    );
  }

  getSubscriptions() {
    return this.http.get<WidgetDesc[]>(GlobalVariable.API_URL + '/subscriptions', { headers: this.initHeader()});
  }
}
