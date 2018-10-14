import { WidgetDesc } from './widget-desc';

export class ServiceDesc {
  name: string;
  description: string;
  widgets: WidgetDesc[];


  constructor(name: string, description: string, widgets: WidgetDesc[]) {
    this.name = name;
    this.description = description;
    this.widgets = widgets;
  }
}
