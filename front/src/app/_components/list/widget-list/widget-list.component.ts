import {Component, HostBinding, Input, OnInit} from '@angular/core';
import {WidgetDesc} from '../../../_class/widget/widget-desc';

@Component({
  selector: 'app-widget-list',
  templateUrl: './widget-list.component.html',
  styleUrls: ['./widget-list.component.css']
})
export class WidgetListComponent implements OnInit {

  @Input('widgets') widgets: WidgetDesc[];

  itemsPerRow: number;


  constructor() {
    this.itemsPerRow = 3;
  }

  ngOnInit() {
  }

}
