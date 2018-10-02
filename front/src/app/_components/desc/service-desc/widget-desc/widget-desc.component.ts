import {Component, Input, OnInit} from '@angular/core';
import {WidgetDesc} from '../../../../_class/widget-desc';

@Component({
  selector: 'app-widget-desc',
  templateUrl: './widget-desc.component.html',
  styleUrls: ['./widget-desc.component.css']
})
export class WidgetDescComponent implements OnInit {

  @Input('widget-desc') widget: WidgetDesc;

  ngOnInit(): void {
  }
}
