import {Component} from '@angular/core';
import {DropEvent} from 'angular-draggable-droppable';
import {Aled} from '../../../_class/aled';

@Component({
  selector: 'app-grid',
  templateUrl: './grid.component.html',
  styleUrls: ['./grid.component.css']
})
export class GridComponent {

  droppedData = '';

  aled: Aled[];

  onDrop({ dropData }: DropEvent<string>): void {
    this.droppedData = dropData;
    setTimeout(() => {
      this.droppedData = '';
    }, 2000);
  }

  getNumber(nbr: number) {
    return Array(nbr + 12 - nbr % 12);
  };
}
