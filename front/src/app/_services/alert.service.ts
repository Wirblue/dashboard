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

  addAlert(err: Alert): void {
    this._alerts.push(err);
  }

  get alerts(): Alert[] {
    return this._alerts;
  }
}

