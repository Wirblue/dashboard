import { Component, OnInit } from '@angular/core';
import { LoginService } from '../../_services/login.service';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { LoginLogin } from '../../_class/login-login';
import {catchError, first} from 'rxjs/operators';
import {LoginRegister} from '../../_class/login-register';
import {Observable, of} from 'rxjs';
import index from '@angular/cli/lib/cli';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {

  registerForm: FormGroup;
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
    this.registerForm = this.formBuilder.group({
      username: ['', Validators.required],
      password: ['', Validators.required],
      passwordConfirmation: ['', Validators.required]
    });

    this.loginService.logout();
    this.returnUrl = this.route.snapshot.queryParams['returnUrl'] || '/';
  }

  get f() { return this.registerForm.controls; }

  onSubmit() {
    this.submitted = true;
    if (this.registerForm.invalid) {
      return;
    }
    this.loading = true;
    this.loginService.register(new LoginRegister(this.f.username.value, this.f.password.value, this.f.passwordConfirmation.value))
      .pipe(first())
      .subscribe(
        data => {
          console.log('Login OK');
          this.router.navigateByUrl('/login');
        },
        error => {
          console.log('Login Error');
          this.loginService.handleError(error);
          this.loading = false;
        });
  }

}
