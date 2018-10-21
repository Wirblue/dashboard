import {Component, Input, OnInit} from '@angular/core';
import { WidgetParam } from '../../../../_class/widget/widget-param';

@Component({
  selector: 'app-param',
  templateUrl: './param.component.html',
  styleUrls: ['./param.component.css']
})
export class ParamComponent implements OnInit {

  constructor() {
}

  @Input('param') param: WidgetParam;

  a = {
    'integer': 'number',
    'password': 'password',
    'boolean': 'checkbox',
    'string': 'text',
    'date': 'date'
  };

  ngOnInit() {
  }

  getParamType(type: string): string {
    if (this.a[type]) {
      return this.a[type];
    }
    return 'password';
  }

}
