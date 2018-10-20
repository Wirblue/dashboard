import { Component, OnInit } from '@angular/core';
import {SubscriptionsService} from '../../_services/subscriptions.service';
import {GridWidgetService} from '../../_services/grid-widget.service';

@Component({
  selector: 'app-list',
  templateUrl: './list.component.html',
  styleUrls: ['./list.component.css']
})
export class ListComponent implements OnInit {

  constructor(private gridWidgetService: GridWidgetService) {
}

  ngOnInit() {
    this.refreshWidgets();
  }

  refreshWidgets() {
    this.gridWidgetService.refreshWidgets();
  }

  getWidgets() {
    return this.gridWidgetService.widgets;
  }
}
