import { Component, OnInit } from '@angular/core';
import {SubscriptionsService} from '../../_services/subscriptions.service';
import {Widget} from '../../_class/widget';

@Component({
  selector: 'app-list',
  templateUrl: './list.component.html',
  styleUrls: ['./list.component.css']
})
export class ListComponent implements OnInit {

  constructor(private subscriptionsService: SubscriptionsService) { }

  widgets: Widget[];

  ngOnInit() {
    this.getWidgets();
  }

  getWidgets() {
    this.subscriptionsService.getWidgets().subscribe(
      widgets => this.widgets = widgets
    );
  }

}
