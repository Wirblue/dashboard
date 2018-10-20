import { Component, Input, OnInit } from '@angular/core';
import { WidgetService } from '../../../../_services/widget.service';
import { DomSanitizer } from '@angular/platform-browser';
import { WidgetDesc } from '../../../../_class/widget/widget-desc';
import { WidgetData, WidgetDataError } from '../../../../_class/widget/widget-data';
import { SubscriptionsService } from '../../../../_services/subscriptions.service';
import { AlertService } from '../../../../_services/alert.service';
import {IntervalService} from '../../../../_services/interval.service';
import {MatDialog} from '@angular/material';
import {EditWidgetDialogComponent} from '../../../desc/service-desc/edit-widget-dialog/edit-widget-dialog.component';
import {GridWidgetService} from '../../../../_services/grid-widget.service';

@Component({
  selector: 'app-widget',
  templateUrl: './widget.component.html',
  styleUrls: ['./widget.component.css']
})
export class WidgetComponent implements OnInit {

  @Input() id: number;

  constructor(private widgetService: WidgetService,
              private sanitizer: DomSanitizer,
              private subscriptionsService: SubscriptionsService,
              private intervalService: IntervalService,
              private alertService: AlertService,
              private dialog: MatDialog,
              private gridWidgetService: GridWidgetService) {
  }

  data: WidgetData;
  desc: WidgetDesc;
  updateDate: Date;

  ngOnInit() {
    this.setUpdateTime(new Date());
    this.init();
  }

  init() {
    console.log(this.id);
    this.widgetService.get(this.id).subscribe(
      data => {
        this.data = data.data;
        this.desc = data.widget;
        this.intervalService.add(this.id, this);
        this.intervalService.start(this.id, this.desc.refresh_time);
        this.setUpdateTime(new Date());
      },
      error => {
        this.alertService.addAlert('Update Widget ' + this.id, error.error.message);
        this.data = new WidgetDataError(error.error.message);
      }
    );
  }

  get() {
    this.widgetService.get(this.id).subscribe(
      data => {
        this.data = data.data;
        this.desc = data.widget;
        this.setUpdateTime(new Date());
      },
      error => {
        this.alertService.addAlert('Update Widget ' + this.id, error.error.message);
        this.data = new WidgetDataError(error.error.message);
        this.intervalService.stop(this.id);
      }
    );
  }

  private update() {
    this.intervalService.stop(this.id);
    this.widgetService.update(this.desc).subscribe(
      data => {
        this.get();
        this.intervalService.start(this.desc.id, this.desc.refresh_time);
        this.setUpdateTime(new Date());
      },
      error => {
        this.alertService.addAlert('Update Widget ' + this.id, error.error.message);
        this.data = new WidgetDataError(error.error.message);
        this.intervalService.stop(this.id);
      }
    );
  }

  edit(): void {
    this.dialog.open(EditWidgetDialogComponent, {
      width: '500px',
      data: this.desc
    }).afterClosed().subscribe(() => {
      this.update();
    });
  }

  delete() {
    this.widgetService.delete(this.id).subscribe(
      data => {
        this.intervalService.stop(this.id);
        this.gridWidgetService.remove(this.id);
      },
      error => {
        this.alertService.addAlert('Delete Widget ' + this.id, error.error.message);
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
