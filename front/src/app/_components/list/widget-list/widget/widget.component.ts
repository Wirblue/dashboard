import { Component, Input, OnInit } from '@angular/core';
import { WidgetService } from '../../../../_services/widget.service';
import { DomSanitizer } from '@angular/platform-browser';
import { WidgetDesc } from '../../../../_class/widget/widget-desc';
import { WidgetData, WidgetDataError } from '../../../../_class/widget/widget-data';
import { interval } from 'rxjs';
import { SubscriptionsService } from '../../../../_services/subscriptions.service';
import { AlertService } from '../../../../_services/alert.service';
import {IntervalService} from '../../../../_services/interval.service';
import {MatDialog} from '@angular/material';
import {AddWidgetDialogComponent} from '../../../desc/service-desc/add-widget-dialog/add-widget-dialog.component';
import {EditWidgetDialogComponent} from '../../../desc/service-desc/edit-widget-dialog/edit-widget-dialog.component';

@Component({
  selector: 'app-widget',
  templateUrl: './widget.component.html',
  styleUrls: ['./widget.component.css']
})
export class WidgetComponent implements OnInit {

  constructor(private widgetService: WidgetService,
              private sanitizer: DomSanitizer,
              private subscriptionsService: SubscriptionsService,
              private intervalService: IntervalService,
              private alertService: AlertService,
              private dialog: MatDialog) {
  }

  @Input('widget') widget: WidgetDesc;

  data: WidgetData;
  updateDate: Date;

  ngOnInit() {
    this.setUpdateTime(new Date());
    this.intervalService.add(this.widget.id, this);
    this.intervalService.start(this.widget.id, this.widget.refresh_time);
    this.get();
  }

  get() {
    this.widgetService.get(this.widget.id).subscribe(
      data => {
        this.data = data.data;
        this.setUpdateTime(new Date());
      },
      error => {
        this.alertService.addAlert('Update Widget ' + this.widget.id, error.error.message);
        this.data = new WidgetDataError(error.error.message);
        this.intervalService.stop(this.widget.id);
      }
    );
  }

  private update() {
    this.intervalService.stop(this.widget.id);
    this.widgetService.update(this.widget).subscribe(
      data => {
        this.get();
        this.intervalService.start(this.widget.id, this.widget.refresh_time);
        this.setUpdateTime(new Date());
      },
      error => {
        this.alertService.addAlert('Update Widget ' + this.widget.id, error.error.message);
        this.data = new WidgetDataError(error.error.message);
        this.intervalService.stop(this.widget.id);
      }
    );
  }

  edit(): void {
    this.dialog.open(EditWidgetDialogComponent, {
      width: '500px',
      data: this.widget
    }).afterClosed().subscribe(() => {
      this.update();
    });
  }

  delete() {
    this.widgetService.delete(this.widget.id).subscribe(
      data => {
        this.intervalService.stop(this.widget.id);
        this.subscriptionsService.remove(this.widget.id);
      },
      error => {
        this.alertService.addAlert('Delete Widget ' + this.widget.id, error.error.message);
        this.data = new WidgetDataError(error.error.message);
      }
    );
  }

  setUpdateTime(date: Date) {
    this.updateDate = date;
  }

  iconCSS() {
    return {
      'background': 'url("' + this.data.icon_image + '")',
      'background-size': 'cover'
    };
  }

  backgroundCSS() {
    return {
      'background': this.data.background_image ? 'url("' + this.data.background_image + '")' : 'inherit',
      'background-size': 'cover'
    };
  }
}
