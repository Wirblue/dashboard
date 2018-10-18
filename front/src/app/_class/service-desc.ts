import { WidgetDesc } from './widget/widget-desc';
import { AuthServiceDesc } from './auth-service-desc';
import {Color} from './color';

export class ServiceDesc {

  name: string;
  description: string;
  widgets: WidgetDesc[];
  auth_service: AuthServiceDesc;
  display_color: Color;

  constructor(name: string, description: string, widgets: WidgetDesc[], authService: AuthServiceDesc, display_color: Color) {
    this.name = name;
    this.description = description;
    this.widgets = widgets;
    this.auth_service = authService;
    this.display_color = display_color;
  }
}
