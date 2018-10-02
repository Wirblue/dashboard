import { Component, OnInit } from '@angular/core';
import { LoginLogin } from '../../_class/login-login';
import {LoginService} from '../../_services/login.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  constructor(private loginService: LoginService) {
    this.user = new LoginLogin('', '');
  }

  user: LoginLogin;

  login(): void {
    console.log(this.user);
    this.loginService.login(this.user);
  };

  ngOnInit() {
  }

}
