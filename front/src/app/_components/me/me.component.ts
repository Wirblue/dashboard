import { Component, OnInit } from '@angular/core';
import {LoginService} from '../../_services/login.service';
import {Router} from '@angular/router';

@Component({
  selector: 'app-me',
  templateUrl: './me.component.html',
  styleUrls: ['./me.component.css']
})
export class MeComponent implements OnInit {

  constructor(private loginService: LoginService, private router: Router) { }

  me: string;

  ngOnInit() {
    this.getMe();
  }

  logout() {
    this.loginService.logout();
    this.router.navigateByUrl('/login');
  }

  getMe() {
    this.loginService.me().subscribe(data => this.me = data.username);
  }

}
