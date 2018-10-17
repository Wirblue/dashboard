import {Component, Input, OnInit} from '@angular/core';
import { ServiceDesc } from '../../../_class/service-desc';
import { WidgetDesc } from '../../../_class/widget/widget-desc';
import {ServicesService} from '../../../_services/services.service';
import {SubscriptionsService} from '../../../_services/subscriptions.service';
import {AlertService} from '../../../_services/alert.service';

@Component({
  selector: 'app-service-desc',
  templateUrl: './service-desc.component.html',
  styleUrls: ['./service-desc.component.css']
})
export class ServiceDescComponent implements OnInit {

  @Input('service-desc') service: ServiceDesc;

  constructor(private serviceService: ServicesService,
              private subscriptionsService: SubscriptionsService,
              private alertService: AlertService) {
  }

  ngOnInit(): void {
  }

  subscribe(widget: WidgetDesc): void {
    this.serviceService.subscribe(this.service, widget).subscribe(
      data => this.subscriptionsService.add(data),
      error => this.alertService.addAlert('subscribe', error.error.message)
    );
  }

}
