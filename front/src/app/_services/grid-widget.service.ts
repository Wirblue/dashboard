import { Injectable } from '@angular/core';
import {IntervalService} from './interval.service';
import {SubscriptionsService} from './subscriptions.service';
import {AlertService} from './alert.service';

@Injectable({
  providedIn: 'root'
})
export class GridWidgetService {

  constructor(private intervaleService: IntervalService,
              private subscriptionsServices: SubscriptionsService,
              private alertService: AlertService) {
  }

  private _widgets = [];

  get widgets(): number[] {
    return this._widgets;
  }

  remove(id: number): void {
    const pos = this._widgets.map(x => {
      return x.id;
    }).indexOf(id);
    this._widgets.splice(pos, 1);
  }

  add(id: number) {
    this._widgets.push({x: 0, y: 0, cols: 1, rows: 1, id: id});
  }

  refreshWidgets() {
    this.intervaleService.stopAll();
    this.subscriptionsServices.getSubscriptions().subscribe(
      data => { for (const o of data) { this.add(o.id); } },
      error => this.alertService.addAlert('refreshWidgets', error.error.message)
    );
  }
}
