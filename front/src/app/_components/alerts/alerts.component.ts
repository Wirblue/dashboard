import { Component, OnInit } from '@angular/core';
import {AlertService} from '../../_services/alert.service';
import {Alert} from '../../_class/alert';
import {MatDialog} from '@angular/material';
import {AlertDialogComponent} from './alert-dialog/alert-dialog.component';

@Component({
  selector: 'app-alerts',
  templateUrl: './alerts.component.html',
  styleUrls: ['./alerts.component.css']
})
export class AlertsComponent implements OnInit {

  constructor(private alertService: AlertService,
              private dialog: MatDialog) {
  }

  ngOnInit() {
  }

  getAlerts(): Alert[] {
    return this.alertService.alerts;
  }

  open() {
    this.dialog.open(AlertDialogComponent, {
      width: '80%',
      data: this.getAlerts()
    }).afterClosed().subscribe(() => this.alertService.clear());
  }
}
