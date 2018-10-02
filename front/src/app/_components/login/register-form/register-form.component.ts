import { Component, OnInit } from '@angular/core';
import {LoginRegister} from '../../../_class/login-register';
import {LoginService} from '../../../_services/login.service';

@Component({
  selector: 'app-register-form',
  templateUrl: './register-form.component.html',
  styleUrls: ['./register-form.component.css']
})
export class RegisterFormComponent implements OnInit {

  constructor(private loginService: LoginService) {
    this.user = new LoginRegister('', '', '')
  }

  user: LoginRegister;

  register(): void {
    console.log(this.user);
    this.loginService.register(this.user);
  }

  ngOnInit() {
  }

}
