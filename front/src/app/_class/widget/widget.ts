import { WidgetDesc } from './widget-desc';
import {WidgetData} from './widget-data';

export class Widget {
  widget: WidgetDesc;
  data: WidgetData;


  constructor(desc: WidgetDesc, data: WidgetData) {
    this.widget = desc;
    this.data = data;
  }
}

