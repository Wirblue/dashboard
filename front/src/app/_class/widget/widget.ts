import { WidgetDesc } from './widget-desc';
import {WidgetData} from './widget-data';

export class Widget {
  desc: WidgetDesc;
  data: WidgetData;


  constructor(desc: WidgetDesc, data: WidgetData) {
    this.desc = desc;
    this.data = data;
  }
}

