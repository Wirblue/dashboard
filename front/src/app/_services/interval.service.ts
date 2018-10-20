import { Injectable } from '@angular/core';
import {Interval} from '../_class/interval';
import {WidgetDesc} from '../_class/widget/widget-desc';
import {WidgetComponent} from '../_components/list/widget-list/widget/widget.component';

@Injectable({
  providedIn: 'root'
})
export class IntervalService {

  constructor() {}

  private _intervals = [];

  start(id: number, refresh_time: number) {
    console.log('START 1', id, refresh_time);
    if (this._intervals[id]) {
      this._intervals[id].start(refresh_time);
    }
  }

  stop(id: number) {
    if (this._intervals[id]) {
      this._intervals[id].stop();
    }
  }

  stopAll() {
    for (const i of this._intervals) {
      if (i) {
        i.stop();
      }
    }
  }

  add(id: number, widgetComp: WidgetComponent) {
    this._intervals[id] = new Interval(id, widgetComp);
  }
}
