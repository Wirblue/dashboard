import { Component, OnInit } from '@angular/core';
import {SubscriptionsService} from '../../_services/subscriptions.service';

@Component({
  selector: 'app-list',
  templateUrl: './list.component.html',
  styleUrls: ['./list.component.css']
})
export class ListComponent implements OnInit {

  constructor(private subscriptionsService: SubscriptionsService) {
}

  ngOnInit() {
    this.refreshWidgets();
  }

  refreshWidgets() {
    this.subscriptionsService.refreshWidgets();
  }

  getWidgets() {
    return this.subscriptionsService.widgets;
  }
}
