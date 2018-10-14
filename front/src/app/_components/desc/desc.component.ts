import {Component, OnInit} from '@angular/core';
import {ServicesService} from '../../_services/services.service';
import {ServiceDesc} from '../../_class/service-desc';

@Component({
  selector: 'app-desc',
  templateUrl: './desc.component.html',
  styleUrls: ['./desc.component.css']
})
export class DescComponent implements OnInit {

  constructor(private descService: ServicesService) {
  }

  services: ServiceDesc[];

  ngOnInit() {
    this.getServices();
  }

  getServices(): void {
    this.descService.getServices().subscribe(services => this.services = services);
  }

}
