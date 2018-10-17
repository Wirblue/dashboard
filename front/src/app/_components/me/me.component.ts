import { Component, OnInit } from '@angular/core';
import {LoginService } from '../../_services/login.service';
import { Router } from '@angular/router';
import { User } from '../../_class/user';
import {AlertService} from '../../_services/alert.service';

@Component({
  selector: 'app-me',
  templateUrl: './me.component.html',
  styleUrls: ['./me.component.css']
})
export class MeComponent implements OnInit {

  constructor(private loginService: LoginService,
              private router: Router,
              private alertService: AlertService) {
  }

  me: User;

  ngOnInit() {
    this.getMe();
  }

  logout() {
    this.loginService.logout();
    this.router.navigateByUrl('/login');
  }

  getMe() {
    this.loginService.me().subscribe(
      data => this.me = data,
      error => this.alertService.addAlert('getMe', error.error.message
      ));
  }

}
