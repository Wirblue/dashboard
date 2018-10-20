import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { first } from 'rxjs/operators';

import { LoginService } from '../../../_services/login.service';
import { LoginLogin } from '../../../_class/login-login';
import {CookieService} from 'ngx-cookie-service';
import {GlobalVariable} from '../../../globals';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['../login.component.css']
})
export class LoginComponent implements OnInit {
  loginForm: FormGroup;
  loading =  false;
  submitted = false;
  returnUrl: string;

  constructor(
    private formBuilder: FormBuilder,
    private route: ActivatedRoute,
    private router: Router,
    private loginService: LoginService,
    private cookieService: CookieService) {
  }

  ngOnInit() {
    this.loginForm = this.formBuilder.group({
      username: ['', Validators.required],
      password: ['', Validators.required]
    });

    this.returnUrl = this.route.snapshot.queryParams['returnUrl'] || '/';

    if (this.cookieService.check(GlobalVariable.COOKIE_NAME)) {
      this.login(this.cookieService.get(GlobalVariable.COOKIE_NAME));
    }
  }

  get f() { return this.loginForm.controls; }

  onSubmit() {
    this.submitted = true;
    if (this.loginForm.invalid) {
      return;
    }
    this.loading = true;
    this.loginService.login(new LoginLogin(this.f.username.value, this.f.password.value))
      .pipe(first())
      .subscribe(
        data => {
          this.login(data.headers.get('Authorization'));
        },
        error => {
          this.loginService.handleError(error);
          this.loading = false;
        });
  }

  login(token: string) {
    this.cookieService.set(GlobalVariable.COOKIE_NAME, token);
    this.loginService.setToken(token);
    this.router.navigateByUrl('/');
  }

}
