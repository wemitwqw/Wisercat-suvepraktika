import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AuthenticationService } from 'src/app/_service/authentication.service';

@Component({
  selector: 'app-logout',
  template: `<div></div>`,
  styleUrls: []
})
export class LogoutComponent implements OnInit {
  constructor(private router: Router, private authenticationService: AuthenticationService) { }

  ngOnInit(): void {
    this.authenticationService.logout()
    this.router.navigate(['/login'])
  }
}
