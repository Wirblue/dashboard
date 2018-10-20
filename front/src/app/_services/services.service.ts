import { Injectable } from '@angular/core';
import { Service } from '../_class/service';
import { Observable, of } from 'rxjs';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { GlobalVariable } from '../globals';
import { LoginService } from './login.service';
import { WidgetDesc } from '../_class/widget/widget-desc';

@Injectable({
  providedIn: 'root'
})
export class ServicesService {

  constructor(private http: HttpClient, private loginService: LoginService) {}

  private initHeader(): HttpHeaders {
    return new HttpHeaders({
      'Content-Type': 'application/json',
      'authorization': this.loginService.getToken()
    });
  }

  getServices(): Observable<Service[]> {
    return this.http.get<Service[]>(GlobalVariable.API_URL + '/services', { headers: this.initHeader() });
  }

  subscribe(service: Service, widget: WidgetDesc): Observable<WidgetDesc> {
    const route = GlobalVariable.API_URL + '/services/' + service.name + '/widgets/' + widget.name + '/subscribe';
    return this.http.post<WidgetDesc>(route, { params: widget.params }, { headers: this.initHeader() });
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
