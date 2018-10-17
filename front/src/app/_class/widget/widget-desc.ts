import {WidgetParam} from './widget-param';

export class WidgetDesc {
  id: number;
  name: string;
  description: string;
  params: WidgetParam[];
  refresh_time: number;

  constructor(name: string, description: string, params: WidgetParam[]) {
    this.name = name;
    this.description = description;
    this.params = params;
  }
}
