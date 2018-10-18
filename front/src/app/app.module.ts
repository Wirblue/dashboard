import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms'; // <-- NgModel lives here

import { AppComponent } from './app.component';
import { AppRoutingModule } from './app-routing.module';

import { DescComponent } from './_components/desc/desc.component';
import { ServiceDescComponent } from './_components/desc/service-desc/service-desc.component';
import { HttpModule } from '@angular/http';
import { HttpClientModule } from '@angular/common/http';
import { ReactiveFormsModule } from '@angular/forms';
import { GridsterModule } from 'angular-gridster2';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { NoopAnimationsModule } from '@angular/platform-browser/animations';
import {
  MatButtonModule,
  MatCardModule,
  MatCheckboxModule, MatDialogModule, MatExpansionModule, MatGridListModule, MatIconModule,
  MatInputModule, MatListModule,
  MatMenuModule,
  MatRippleModule, MatSidenavModule,
  MatToolbarModule, MatTreeModule
} from '@angular/material';

import { RegisterComponent } from './_components/login/register/register.component';
import { LoginComponent } from './_components/login/login/login.component';
import { AuthGuard } from './_guards/auth.guard';
import { LogGuard } from './_guards/log.guard';
import { AlertsComponent } from './_components/alerts/alerts.component';
import { HomeComponent } from './_components/home/home.component';
import { DragAndDropModule } from 'angular-draggable-droppable';
import { ParamComponent } from './_components/desc/service-desc/param/param.component';
import { WidgetComponent } from './_components/list/widget-list/widget/widget.component';
import { WidgetListComponent } from './_components/list/widget-list/widget-list.component';
import { ListComponent } from './_components/list/list.component';
import { MeComponent } from './_components/me/me.component';
import { AddWidgetDialogComponent } from './_components/desc/service-desc/add-widget-dialog/add-widget-dialog.component';
import { EditWidgetDialogComponent } from './_components/desc/service-desc/edit-widget-dialog/edit-widget-dialog.component';

@NgModule({
  imports: [
    AppRoutingModule,
    BrowserModule,
    FormsModule,
    HttpModule,
    HttpClientModule,
    ReactiveFormsModule,
    DragAndDropModule,
    GridsterModule,
    BrowserAnimationsModule,
    NoopAnimationsModule,
    MatButtonModule,
    MatCheckboxModule,
    MatInputModule,
    MatRippleModule,
    MatCardModule,
    MatToolbarModule,
    MatMenuModule,
    MatSidenavModule,
    MatExpansionModule,
    MatListModule,
    MatTreeModule,
    MatGridListModule,
    MatIconModule,
    MatDialogModule
  ],

  declarations: [
    AppComponent,
    DescComponent,
    ServiceDescComponent,
    LoginComponent,
    RegisterComponent,
    LoginComponent,
    AlertsComponent,
    HomeComponent,
    ParamComponent,
    WidgetComponent,
    WidgetListComponent,
    ListComponent,
    MeComponent,
    AddWidgetDialogComponent,
    EditWidgetDialogComponent
  ],

  providers: [
    AuthGuard,
    LogGuard
  ],
  bootstrap: [ AppComponent ],
  entryComponents: [ AddWidgetDialogComponent, EditWidgetDialogComponent ]
})
export class AppModule {
}
