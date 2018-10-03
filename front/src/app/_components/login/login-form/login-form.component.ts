import { Component, OnInit } from '@angular/core';
import { LoginLogin } from '../../../_class/login-login';
import {LoginService} from '../../../_services/login.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login-form',
  templateUrl: './login-form.component.html',
  styleUrls: ['./login-form.component.css']
})
export class LoginFormComponent implements OnInit {

  constructor(private loginService: LoginService, private router: Router) {
    this.user = new LoginLogin('', '');
  }

  user: LoginLogin;

  login(): void {
    console.log(this.user);
    this.loginService.login(this.user);
    this.router.navigateByUrl('/');
  };

  ngOnInit() {
  }

}
