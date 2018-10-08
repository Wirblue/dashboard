import { Component, OnInit } from '@angular/core';
import {AlertService} from '../../_services/alert.service';
import {Alert} from '../../_class/alert';

@Component({
  selector: 'app-alerts',
  templateUrl: './alerts.component.html',
  styleUrls: ['./alerts.component.css']
})
export class AlertsComponent implements OnInit {

  constructor(private alertService: AlertService) { }

  ngOnInit() {
  }

  getAlerts(): Alert[] {
    return this.alertService.alerts;
  }
}
