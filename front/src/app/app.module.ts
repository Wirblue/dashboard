import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms'; // <-- NgModel lives here

import { AppComponent } from './app.component';

import { DescComponent } from './_components/desc/desc.component';
import { ServiceDescComponent } from './_components/desc/service-desc/service-desc.component';
import { WidgetDescComponent} from './_components/desc/service-desc/widget-desc/widget-desc.component';
import { HttpModule } from '@angular/http';
import { HttpClientModule } from '@angular/common/http';
import { LoginComponent } from './_components/login/login.component';
import { RegisterComponent } from './_components/login/register/register.component';

@NgModule({
  declarations: [
    AppComponent,
    DescComponent,
    ServiceDescComponent,
    WidgetDescComponent,
    LoginComponent,
    RegisterComponent
  ],

  imports: [
    BrowserModule,
    FormsModule,
    HttpModule,
    HttpClientModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
