import {Component, Inject, OnInit} from '@angular/core';
import {MAT_DIALOG_DATA, MatDialogRef} from '@angular/material';
import {AddWidgetDialogComponent} from '../add-widget-dialog/add-widget-dialog.component';
import {WidgetDesc} from '../../../../_class/widget/widget-desc';

@Component({
  selector: 'app-edit-widget-dialog',
  templateUrl: './edit-widget-dialog.component.html',
  styleUrls: ['./edit-widget-dialog.component.css']
})
export class EditWidgetDialogComponent implements OnInit {

  constructor(public dialogRef: MatDialogRef<EditWidgetDialogComponent>,
              @Inject(MAT_DIALOG_DATA) public data: WidgetDesc) {
  }

  ngOnInit() {
  }

}
