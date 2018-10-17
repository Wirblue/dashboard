import { Component, Input, OnInit } from '@angular/core';
import { WidgetService } from '../../../../_services/widget.service';
import { DomSanitizer } from '@angular/platform-browser';
import { WidgetDesc } from '../../../../_class/widget/widget-desc';
import { WidgetData, WidgetDataError } from '../../../../_class/widget/widget-data';
import { interval } from 'rxjs';
import { SubscriptionsService } from '../../../../_services/subscriptions.service';
import { AlertService } from '../../../../_services/alert.service';
import {IntervalService} from '../../../../_services/interval.service';

@Component({
  selector: 'app-widget',
  templateUrl: './widget.component.html',
  styleUrls: ['./widget.component.css']
})
export class WidgetComponent implements OnInit {

  @Input('widget') widget: WidgetDesc;

  constructor(private widgetService: WidgetService,
              private sanitizer: DomSanitizer,
              private subscriptionsService: SubscriptionsService,
              private intervalService: IntervalService,
              private alertService: AlertService) {
  }

  edit = false;
  data: WidgetData;

  ngOnInit() {
    this.intervalService.add(this.widget.id, this);
    this.intervalService.start(this.widget.id, this.widget.refresh_time);
    this.get();
  }

  get() {
    this.widgetService.get(this.widget.id).subscribe(
      data => {
        this.data = data.data;
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
      },
      error => {
        this.alertService.addAlert('Update Widget ' + this.widget.id, error.error.message);
        this.data = new WidgetDataError(error.error.message);
        this.intervalService.stop(this.widget.id);
      }
    );
  }

  private delete() {
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

  backgroundCSS() {
    return {
      'background': this.data.background_image ? 'url("' + this.data.background_image + '")' : 'red',
      'background-size': 'cover'
    };
  }
}
