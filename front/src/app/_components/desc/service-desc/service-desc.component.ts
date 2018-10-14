import {Component, Input, OnInit} from '@angular/core';
import { ServiceDesc } from '../../../_class/service-desc';
import {WidgetDesc, WidgetDestParams} from '../../../_class/widget-desc';
import {ServicesService} from '../../../_services/services.service';

@Component({
  selector: 'app-service-desc',
  templateUrl: './service-desc.component.html',
  styleUrls: ['./service-desc.component.css']
})
export class ServiceDescComponent implements OnInit {

  @Input('service-desc') service: ServiceDesc;

  constructor(private serviceService: ServicesService) {
  }

  ngOnInit(): void {
  }

  subscribe(widget: WidgetDesc): void {
    this.serviceService.subscribe(this.service, widget, [new WidgetDestParams('city', 'nancy')]).subscribe();
  }

}
