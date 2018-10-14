import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms'; // <-- NgModel lives here

import { AppComponent } from './app.component';
import { AppRoutingModule } from './app-routing.module';

import { DescComponent } from './_components/desc/desc.component';
import { ServiceDescComponent } from './_components/desc/service-desc/service-desc.component';
import { WidgetDescComponent} from './_components/desc/service-desc/widget-desc/widget-desc.component';
import { HttpModule } from '@angular/http';
import { HttpClientModule } from '@angular/common/http';
import { ReactiveFormsModule } from '@angular/forms';

import { RegisterComponent } from './_components/register/register.component';
import { LoginComponent } from './_components/login/login.component';
import { AuthGuard } from './_guards/auth.guard';
import { LogGuard } from './_guards/log.guard';
import { AlertsComponent } from './_components/alerts/alerts.component';
import { HomeComponent } from './_components/home/home.component';
import { GridComponent } from './_components/home/grid/grid.component';
import {DragAndDropModule} from 'angular-draggable-droppable';

@NgModule({
  declarations: [
    AppComponent,
    DescComponent,
    ServiceDescComponent,
    WidgetDescComponent,
    LoginComponent,
    RegisterComponent,
    LoginComponent,
    AlertsComponent,
    HomeComponent,
    GridComponent
  ],

  imports: [
    AppRoutingModule,
    BrowserModule,
    FormsModule,
    HttpModule,
    HttpClientModule,
    ReactiveFormsModule,
    DragAndDropModule
  ],
  providers: [
    AuthGuard,
    LogGuard
  ],
  bootstrap: [ AppComponent ]
})
export class AppModule {
}
