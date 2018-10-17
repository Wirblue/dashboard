import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { first } from 'rxjs/operators';

import { LoginService } from '../../../_services/login.service';
import { LoginLogin } from '../../../_class/login-login';

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
    private loginService: LoginService) {
  }

  ngOnInit() {
    this.loginForm = this.formBuilder.group({
      username: ['', Validators.required],
      password: ['', Validators.required]
    });

    this.loginService.logout();
    this.returnUrl = this.route.snapshot.queryParams['returnUrl'] || '/';
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
          this.loginService.setToken(data.headers.get('Authorization'));
          this.router.navigateByUrl('/');
        },
        error => {
          this.loginService.handleError(error);
          this.loading = false;
        });
  }

}
