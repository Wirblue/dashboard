import {Component, Input, OnInit} from '@angular/core';
import { ServiceDesc } from '../../../_class/service-desc';
import { WidgetDesc } from '../../../_class/widget-desc';

@Component({
  selector: 'app-service-desc',
  templateUrl: './service-desc.component.html',
  styleUrls: ['./service-desc.component.css']
})
export class ServiceDescComponent implements OnInit {

  @Input('service-desc') service: ServiceDesc;

  ngOnInit(): void {
  }

}
