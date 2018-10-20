import { Injectable } from '@angular/core';
import {Alert} from '../_class/alert';

@Injectable({
  providedIn: 'root'
})
export class AlertService {

  constructor() {
    this._alerts = [];
  }

  private _alerts: Alert[];

  addAlert(context: string, message: string): void {
    this._alerts.push(new Alert(context, message, 'alert-danger'));
  }

  get alerts(): Alert[] {
    return this._alerts;
  }

  clear() {
    this._alerts = [];
  }
}

