import { Component, OnInit, OnChanges, SimpleChanges } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { AuthenticationService } from 'src/app/_service/authentication.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  form!: FormGroup;

  authError: boolean = false;

  constructor(private router: Router, private authenticationService: AuthenticationService) {}
  
  ngOnInit() {    
    this.form = new FormGroup({
      username: new FormControl(null, [Validators.required, Validators.maxLength(20), Validators.pattern('[a-zA-Z0-9]+')]),
      password: new FormControl(null, [Validators.required, Validators.maxLength(35), Validators.pattern('[a-zA-Z0-9]+')])
    });
  }

  get f() { return this.form.controls; }

  onSubmit(): void {
    if(this.form.valid){
      const fields = this.f;

      this.authenticationService.login(fields['username'].value, fields['password'].value).subscribe(
        success => {
          if (success) {
            this.router.navigate(['']);
            if (this.authError){
              this.authError = false;
            }
          }
        },
        err => {
          this.authError = true;
          this.form.reset();
        }
      );
    }
  }
}

