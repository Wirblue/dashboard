import { Component, OnInit } from '@angular/core';
import {SubscriptionsService} from '../../_services/subscriptions.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  constructor(private subscriptionsService: SubscriptionsService) { }

  ngOnInit() {
  }

  testSubscriptions() {
    this.subscriptionsService.getSubscription().subscribe();
  }

  testWidgets() {
    this.subscriptionsService.getWidgets().subscribe();
  }
}
