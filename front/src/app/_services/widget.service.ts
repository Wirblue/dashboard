import { Injectable } from '@angular/core';
import {Observable} from 'rxjs';
import {WidgetDesc} from '../_class/widget/widget-desc';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {GlobalVariable} from '../globals';
import {LoginService} from './login.service';
import {Widget} from '../_class/widget/widget';

@Injectable({
  providedIn: 'root'
})
export class WidgetService {

  constructor(private http: HttpClient, private loginService: LoginService) {
}

  private initHeader(): HttpHeaders {
    return new HttpHeaders({
      'Content-Type': 'application/json',
      'authorization': this.loginService.getToken()
    });
  }

  get(id: number): Observable<Widget> {
    return this.http.get<Widget>(GlobalVariable.API_URL + '/subscriptions/' + id, { headers: this.initHeader() });
  }

  update(widget: WidgetDesc): Observable<WidgetDesc> {
    return this.http.put<WidgetDesc>(GlobalVariable.API_URL + '/subscriptions/' + widget.id, widget, { headers: this.initHeader()});
  }

  delete(id: number) {
    return this.http.delete(GlobalVariable.API_URL + '/subscriptions/' + id, { headers: this.initHeader() });
  }
}
