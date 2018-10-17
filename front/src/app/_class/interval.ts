import {interval, Subscription} from 'rxjs';
import { WidgetComponent } from '../_components/list/widget-list/widget/widget.component';

export class Interval {
  id: number;
  component: WidgetComponent;
  interval: Subscription;

  start(refresh: number): void {
    console.log('START INTERVAL ' + this.id);
    this.interval = interval(refresh).subscribe(
      data => this.component.get()
    );
  }

  stop(): void {
    if (this.interval) {
      console.log('STOP INTERVAL ' + this.id);
      this.interval.unsubscribe();
    }
  }

  constructor(id: number, component: WidgetComponent) {
    this.id = id;
    this.component = component;
  }
}
