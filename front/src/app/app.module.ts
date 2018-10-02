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
import { LoginFormComponent } from './_components/login/login-form/login-form.component';
import { RegisterFormComponent } from './_components/login/register-form/register-form.component';
import { LoginComponent } from './_components/login/login.component';

import { AuthGuard } from './_guards/auth.guard';
import { LogGuard } from './_guards/log.guard';

@NgModule({
  declarations: [
    AppComponent,
    DescComponent,
    ServiceDescComponent,
    WidgetDescComponent,
    LoginFormComponent,
    RegisterFormComponent,
    LoginComponent
  ],

  imports: [
    AppRoutingModule,
    BrowserModule,
    FormsModule,
    HttpModule,
    HttpClientModule
  ],
  providers: [
    AuthGuard,
    LogGuard
  ],
  bootstrap: [ AppComponent ]
})
export class AppModule { }
