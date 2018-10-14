import {Component, Input, OnInit} from '@angular/core';
import { Widget } from '../../../../_class/widget';

@Component({
  selector: 'app-widget',
  templateUrl: './widget.component.html',
  styleUrls: ['./widget.component.css']
})
export class WidgetComponent implements OnInit {

  @Input('widget') widget: Widget;

  constructor() { }

  ngOnInit() {
  }

  private backgroundCSS() {
    return this.widget.data.background_image
      ? 'background-image: url(\"' + this.widget.data.background_image + '\");'
      : 'background: #FFFFFF';
  }

}
