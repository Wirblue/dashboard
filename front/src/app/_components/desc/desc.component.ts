import {Component, OnInit} from '@angular/core';
import {ServicesService} from '../../_services/services.service';
import {ServiceDesc} from '../../_class/service-desc';
import {AlertService} from '../../_services/alert.service';

@Component({
  selector: 'app-desc',
  templateUrl: './desc.component.html',
  styleUrls: ['./desc.component.css']
})
export class DescComponent implements OnInit {

  constructor(private descService: ServicesService,
              private alertService: AlertService) {
  }

  services: ServiceDesc[];

  ngOnInit() {
    this.getServices();
  }

  getServices(): void {
    this.descService.getServices().subscribe(
      services => this.services = services,
      error => this.alertService.addAlert('getServices', error.error.message)
    );
  }
}
