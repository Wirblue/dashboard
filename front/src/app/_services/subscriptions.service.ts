import { Injectable } from '@angular/core';
import { Observable, of } from 'rxjs';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { LoginService } from './login.service';
import {GlobalVariable} from '../globals';
import {Widget} from '../_class/widget';

@Injectable({
  providedIn: 'root'
})
export class SubscriptionsService {

  constructor(private http: HttpClient, private loginService: LoginService) {}

  private initHeader(): HttpHeaders {
    return new HttpHeaders({
      'Content-Type': 'application/json',
      'observe': 'response',
      'authorization': this.loginService.getToken()
    });
  }

  getSubscription() {
    return this.http.get(GlobalVariable.BASE_API_URL + '/subscriptions', { headers: this.initHeader()});
  }

  getWidgets(): Observable<Widget[]> {
    return this.http.get<Widget[]>(GlobalVariable.BASE_API_URL + '/subscriptions/widgets', { headers: this.initHeader()});
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
