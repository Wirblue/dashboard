import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from './_components/login/login.component';
import { DescComponent } from './_components/desc/desc.component';
import { AuthGuard } from './_guards/auth.guard';
import { LogGuard } from './_guards/log.guard';

const routes: Routes = [
  { path: '', component: DescComponent, canActivate: [AuthGuard]},
  { path: 'login', component: LoginComponent, canActivate: [LogGuard]},
  { path: '**', redirectTo: ''}
];

@NgModule({
  imports: [ RouterModule.forRoot(routes) ],
  exports: [ RouterModule ],
  declarations: []
})
export class AppRoutingModule { }
