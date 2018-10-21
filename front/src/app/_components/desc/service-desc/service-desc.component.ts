import {Component, Input, OnInit} from '@angular/core';
import { Service } from '../../../_class/service';
import { WidgetDesc } from '../../../_class/widget/widget-desc';
import {ServicesService} from '../../../_services/services.service';
import {SubscriptionsService} from '../../../_services/subscriptions.service';
import {AlertService} from '../../../_services/alert.service';
import {MatDialog} from '@angular/material';
import {AddWidgetDialogComponent} from './add-widget-dialog/add-widget-dialog.component';
import {GlobalVariable} from '../../../globals';
import {LoginService} from '../../../_services/login.service';
import {GridWidgetService} from '../../../_services/grid-widget.service';
import {AuthorizationService} from '../../../_services/authorization.service';

@Component({
  selector: 'app-service-desc',
  templateUrl: './service-desc.component.html',
  styleUrls: ['./service-desc.component.css']
})
export class ServiceDescComponent implements OnInit {

  @Input('service-desc') service: Service;

  constructor(private serviceService: ServicesService,
              private subscriptionsService: SubscriptionsService,
              private dialog: MatDialog,
              private loginService: LoginService,
              private authService: AuthorizationService,
              private alertService: AlertService,
              private gridWidgetService: GridWidgetService) {
  }

  ngOnInit(): void {
  }

  getAuthUrl(): string {
    return GlobalVariable.AUTH_URL + '?redirectUri=' + GlobalVariable.LOCAL_URL + '&externalService=' +
      this.service.auth_service.name + '&token=' + this.loginService.getToken();
  }

  subscribe(widget: WidgetDesc): void {
    this.serviceService.subscribe(this.service, widget).subscribe(
      data => this.gridWidgetService.add(data.id),
      error => this.alertService.addAlert('subscribe', error.error.message)
    );
  }

  openDialog(widget: WidgetDesc): void {
    const dialogRef = this.dialog.open(AddWidgetDialogComponent, {
      width: '500px',
      data: widget
    });
    dialogRef.afterClosed().subscribe(result => {
      this.subscribe(result);
    });
  }

  authLogout() {
    console.log(this.service);
    this.authService.logout(this.service).subscribe();
  }
}
