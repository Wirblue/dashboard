import {Component, Input, OnInit} from '@angular/core';
import {SubscriptionsService} from '../../../_services/subscriptions.service';
import {Widget} from '../../../_class/widget';

@Component({
  selector: 'app-widget-list',
  templateUrl: './widget-list.component.html',
  styleUrls: ['./widget-list.component.css']
})
export class WidgetListComponent implements OnInit {

  @Input('widgets') widgets: Widget[];

  constructor() { }

  ngOnInit() {
  }

}
