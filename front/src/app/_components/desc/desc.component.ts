import {Component, OnInit} from '@angular/core';
import {DescService} from '../../_services/desc.service';
import {ServiceDesc} from '../../_class/service-desc';

@Component({
  selector: 'app-desc',
  templateUrl: './desc.component.html',
  styleUrls: ['./desc.component.css']
})
export class DescComponent implements OnInit {

  constructor(private descService: DescService) {
  }

  services: ServiceDesc[];

  ngOnInit() {
    this.getServices();
  }

  getServices(): void {
    this.descService.getServices().subscribe(services => this.services = services);
  }

}
