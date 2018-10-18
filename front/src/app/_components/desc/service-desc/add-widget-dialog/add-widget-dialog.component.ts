import {Component, Inject, OnInit} from '@angular/core';
import {MAT_DIALOG_DATA, MatDialogRef} from '@angular/material';
import {WidgetDesc} from '../../../../_class/widget/widget-desc';

@Component({
  selector: 'app-add-widget-dialog',
  templateUrl: './add-widget-dialog.component.html',
  styleUrls: ['./add-widget-dialog.component.css']
})
export class AddWidgetDialogComponent implements OnInit {

  constructor(public dialogRef: MatDialogRef<AddWidgetDialogComponent>,
              @Inject(MAT_DIALOG_DATA) public data: WidgetDesc) {
  }

  ngOnInit() {
  }

}
