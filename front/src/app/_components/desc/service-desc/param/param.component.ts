import {Component, Input, OnInit} from '@angular/core';
import {WidgetDestParams} from '../../../../_class/widget-desc';

@Component({
  selector: 'app-param',
  templateUrl: './param.component.html',
  styleUrls: ['./param.component.css']
})
export class ParamComponent implements OnInit {

  @Input('param') param: WidgetDestParams;

  constructor() { }

  ngOnInit() {
  }

}
