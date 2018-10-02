import { WidgetDesc } from './widget-desc';

export class ServiceDesc {
  name: string;
  desc: string;
  widgets: WidgetDesc[];


  constructor(name: string, desc: string, widgets: WidgetDesc[]) {
    this.name = name;
    this.desc = desc;
    this.widgets = widgets;
  }
}
