import { Component, OnInit, OnChanges, SimpleChanges } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { AuthenticationService } from 'src/app/_service/authentication.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnChanges, OnInit {
  form: FormGroup;

  constructor(private router: Router, private authenticationService: AuthenticationService) {}
  
  ngOnInit() {
    this.form = new FormGroup({
      username: new FormControl(null, [Validators.required, Validators.minLength(1), Validators.maxLength(30), Validators.pattern('\b[A-Z][a-zA-Z]*\b')]),
      password: new FormControl(null, [Validators.required, Validators.minLength(8), Validators.maxLength(8), Validators.pattern('[a-z0-9]+')])
    });

    this.authenticationService.login("user1", "password")
  }
  
  ngOnChanges(changes: SimpleChanges): void {
    throw new Error('Method not implemented.');
  }

}
